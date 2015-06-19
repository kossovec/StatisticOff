package ua.kossovec.service;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateFormatTest {
    @Test
    public void dateFormatTest() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH");
        System.out.println("date: " + dateFormat.format(new GregorianCalendar().getTime()));

        System.out.println(Integer.parseInt(dateFormat1.format(new GregorianCalendar().getTime()))+1);

    }

}
