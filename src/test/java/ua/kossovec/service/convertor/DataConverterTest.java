package ua.kossovec.service.convertor;

import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataConverterTest {
    private static final String TEST_DATE = "06/11/2015 2:44 AM";

    @Test
    public void testDateConversation() {  //YYYYMMDDHH00
        String expectDate = "201506181800";
        GregorianCalendar calendar = new GregorianCalendar(2015, 6, 18, 18, 00);
        String actualDate = DateConvertor.covertToSTMMPFormat(calendar);
        Assert.assertEquals(expectDate, actualDate);
    }

    @Test
    public void testDateConversationIfMinutesNotNull() {
        String expectDate = "201506181800";
        GregorianCalendar calendar = new GregorianCalendar(2015, 6, 18, 17, 10);
        String actualDate = DateConvertor.covertToSTMMPFormat(calendar);
        Assert.assertEquals(expectDate, actualDate);
    }

    @Test
    public void testDateConversationIfMonthLessThanTen() {
        String expectDate = "201506181800";
        GregorianCalendar calendar = new GregorianCalendar(2015, 6, 18, 17, 10);
        String actualDate = DateConvertor.covertToSTMMPFormat(calendar);
        Assert.assertEquals(expectDate, actualDate);
    }

    @Test
    public void testDateConversationIfDayLessThanTen() {
        String expectDate = "201506071800";
        GregorianCalendar calendar = new GregorianCalendar(2015, 6, 7, 17, 10);
        String actualDate = DateConvertor.covertToSTMMPFormat(calendar);
        Assert.assertEquals(expectDate, actualDate);
    }

    @Test
    public void testDateConversationIfHourLessThanTen() {
        String expectDate = "201506070900";
        GregorianCalendar calendar = new GregorianCalendar(2015, 6, 7, 9, 00);
        String actualDate = DateConvertor.covertToSTMMPFormat(calendar);
        Assert.assertEquals(expectDate, actualDate);
    }

    @Test
    public void testConvertFromAmPmTo24() throws ParseException {
//      06/11/2015 2:44 AM
        String toParse = "06/11/2015 02:44 PM";
        Calendar expected = new GregorianCalendar(2015, 6, 11, 14, 44);
        Calendar actual = DateConvertor.covertFrom12to24(toParse);
        Assert.assertEquals(expected, actual);
    }

}
