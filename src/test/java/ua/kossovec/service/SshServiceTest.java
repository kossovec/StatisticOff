package ua.kossovec.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.kossovec.service.ssh.SshService;

import java.io.IOException;

@ContextConfiguration("/test-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SshServiceTest {

    @Autowired
    SshService sshService;

    private final static String TEST_IP = "10.49.8.17";

    @Test
    public void loginOKTest() throws IOException {
        Assert.assertNull(sshService.connect(TEST_IP));
    }
    @Test
    public void commandSendTest() {
        String expected = "BSC147APG43B";
        sshService.connect(TEST_IP);
        String commandAnswer = sshService.sendCommand("hostname");
        Assert.assertEquals(expected,commandAnswer.trim());
    }
}

