package ua.kossovec.service;

import com.maverick.ssh.PasswordAuthentication;
import com.maverick.ssh.SshClient;
import com.maverick.ssh.SshConnector;
import com.maverick.ssh.SshException;
import com.sshtools.net.SocketTransport;
import org.junit.Test;

import java.io.IOException;

public class SshTest {
  @Test
  public void sshConnectionTest() throws SshException, IOException {
    SshConnector connector = SshConnector.createInstance();
    SshClient ssh = connector.connect(new SocketTransport("10.44.1.140", 22), "login");
    PasswordAuthentication pwd = new PasswordAuthentication();
    pwd.setPassword("some pass");
    ssh.authenticate(pwd);
    
  }
}
