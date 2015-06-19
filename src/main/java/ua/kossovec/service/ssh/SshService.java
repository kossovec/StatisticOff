package ua.kossovec.service.ssh;

public interface SshService {
    String connect(String ip);
    String sendCommand(String command);
    void close();
}
