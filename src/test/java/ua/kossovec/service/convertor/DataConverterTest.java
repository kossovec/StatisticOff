package ua.kossovec.service.convertor;

import org.junit.Assert;
import org.junit.Test;

import java.util.GregorianCalendar;

public class DataConverterTest {
    @Test
    public void testDateConversationToSTMMPFormat() {  //YYYYMMDDHH00
        String expectDate = "201506181800";
        GregorianCalendar calendar = new GregorianCalendar(2015,5,18,18,00);
        String actualDate = DateConvertor.covertToSTMMPFormat(calendar);
        Assert.assertEquals(expectDate,actualDate);
    }
}
