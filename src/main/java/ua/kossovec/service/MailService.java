package ua.kossovec.service;

public interface MailService {
    void sendMail(String to, String subject, String body);
    void sendPreConfiguredMail(String message);
}
