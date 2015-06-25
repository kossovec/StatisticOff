package ua.kossovec.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.kossovec.dao.NesDaoImpl;
import ua.kossovec.model.Ne;

import java.io.IOException;
import java.util.GregorianCalendar;

public class StatisticOffServiceTest {

    @Mock
    NesDaoImpl neDao;

    @InjectMocks
    StatisticOffServiceImpl offService;

    @Before
    public void init() throws IOException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(neDao.getNeByName(NE_NAME)).thenReturn(new Ne("BSC 147", "10.44.21.12","Kyiv", "kyivs",0));
    }

    private final static String NE_NAME = "BSC 147";

//    @Test
//    public void staticOffTest() {
//        Assert.assertTrue(offService.disableStatistic(NE_NAME, new GregorianCalendar()));
//    }
}
