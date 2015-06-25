package ua.kossovec.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.kossovec.model.Ne;
import ua.kossovec.service.CsvParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NesDaoTest {
    private static final String NE_NAME = "MGW 002";
    private static final int AXE_NE_TYPE = 0;

    private static final String NE_NOT_IN_LIST = "BSC 12151";
    private static final List<String> FILE_DATA = new ArrayList<String>() {{
        add("MGW 001;1;1;0;10.41.1.1;;;23;;;");
        add("MGW 002;1;1;0;10.41.1.1;;;23;;;");
        add("BSC 003;1;1;2;10.41.1.1;;;23;;;");
        add("BSC 010;1;1;0;10.41.1.1;;;23;;;");
        add("BSC 402;1;1;0;10.41.1.1;;;23;;;");
    }};

    @Before
    public void init() throws IOException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(csvParser.getLinesFromCsv()).thenReturn(FILE_DATA);
    }

    @Mock
    CsvParser csvParser;

    @InjectMocks
    NesDaoImpl nesDao;

    @Test
    public void getNeByName() {
        Ne ne = nesDao.getNeByName(NE_NAME);
        Assert.assertEquals(NE_NAME, ne.getName());
    }

    @Test
    public void getAllNes() {
        List<Ne> allNes = nesDao.getAllNes();
        Assert.assertTrue(allNes.size() > 1);
    }

    @Test
    public void getAllAxeNes() {
        List<Ne> allAxeNes = nesDao.getAllAxeNes();
        for (Ne ne : allAxeNes) {
            Assert.assertTrue(ne.getType() == AXE_NE_TYPE);
        }
    }

    @Test
    public void getAllNotLinuxAxeBscTest() {
        List<Ne> allNotLinuxAxeBSC = nesDao.getAllNotLinuxAxeBsc();
        for (Ne ne : allNotLinuxAxeBSC) {
            String name = ne.getName();
            String[] neNameArr = ne.getName().split("\\s+");
            String neNumber = neNameArr[1];
            Assert.assertTrue(Integer.parseInt(neNumber) < 400);
            Assert.assertTrue(name.startsWith("BSC"));
        }
    }

    @Test(expected = NullPointerException.class)
    public void getNeByName_NeNotInFile() {
        Ne ne = nesDao.getNeByName(NE_NOT_IN_LIST);
        ne.getName();
    }

}
