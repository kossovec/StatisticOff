package ua.kossovec.service.ssh;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

public class CommandOutputToDataConvertorTest {

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(sshService.connect(Mockito.anyString())).thenReturn(null);
        Mockito.when(sshService.sendCommand("afpls")).thenReturn(
                "RELCMDHDF                         \n" +
                "RIRTRQ                            \n" +
                "SDMBSC5                           \n");
    }
    @Mock
    SshServiceImpl sshService;

    @Test
    public void getTransferQueueTest() {
        String expect = "SDMBSC5";
        String answer = CommandOutputToDataConvertor.getTransferQueue(sshService);
        Assert.assertEquals(answer,expect);
    }
}
