package ua.kossovec.service.ssh;

import com.jcraft.jsch.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
@Component
public class SshServiceImpl implements SshService {
    private static final Logger LOGGER = Logger.getLogger(SshServiceImpl.class);
    private  JSch sshChannel;
    private  String domain;
    private  String login;
    private  String password;
    private  int port;
    private  int timeOut;
    private Session session;

    public SshServiceImpl(String domain, String login, String password, int port, int timeOut) {
        sshChannel = new JSch();
        this.domain = domain;
        this.login = login;
        this.password = password;
        this.port = port;
        this.timeOut = timeOut;
    }

    public SshServiceImpl() {
    }

    @Override
    public String connect(String ip) {
        String errorMessage = null;
        try {
            session = sshChannel.getSession(domain + "/" + login, ip, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(timeOut);
        } catch (JSchException e) {
            errorMessage = e.getMessage();
        }

        return errorMessage;
    }
    @Override
    public String sendCommand(String command) {
        StringBuilder outputBuffer = new StringBuilder();
        try {
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            InputStream commandOutput = channel.getInputStream();
            channel.connect();
            int readByte = commandOutput.read();

            while (readByte != 0xffffffff) {
                outputBuffer.append((char) readByte);
                readByte = commandOutput.read();
            }

            channel.disconnect();
        } catch (IOException e) {
            logWarning(e.getMessage());
            throw new RuntimeException(e);
        } catch (JSchException e) {
            logError(e.getMessage());
            throw new RuntimeException(e);
        }
        return outputBuffer.toString();
    }

    @Override
    public void close() {
        session.disconnect();
    }

    public static void main(String[] args) {
        SshServiceImpl sshTest = new SshServiceImpl("smdmud","stscheck_script", "OAW8RhJKW8131", 22, 60000);
        String errorMessage = sshTest.connect("10.49.8.17");
        if (errorMessage != null) {
            System.out.println(errorMessage);
        }

        String ansver = sshTest.sendCommand("swrprint");
        System.out.println(ansver);
        sshTest.close();
    }

    private String logError(String errorMessage) {
        if (errorMessage != null) {
            LOGGER.info(errorMessage);
        }

        return errorMessage;
    }

    private String logWarning(String warnMessage) {
        if (warnMessage != null) {
            LOGGER.info(warnMessage);
        }

        return warnMessage;
    }
}
