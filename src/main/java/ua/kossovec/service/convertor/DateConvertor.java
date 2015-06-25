package ua.kossovec.service.convertor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateConvertor {
    public static String covertToSTMMPFormat(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String sMonth = addingZero(month);
        String sDay = addingZero(day);
        if (minute != 0) {
            hour += 1;
        }

        String sHour = addingZero(hour);
        return year + sMonth + sDay + sHour + "00";
    }

    private static String addingZero(int day) {
        return (day < 10) ? "0" + day : "" + day;
    }

    public static Calendar covertFrom12to24(String toParse)  {
        DateFormat dateFormat12 = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
        DateFormat dateFormat24 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date tempDate = null;
        Calendar calendar = new GregorianCalendar();
        try {
            tempDate = dateFormat12.parse(toParse);
            String date = dateFormat24.format(tempDate);
            Date realDate = dateFormat24.parse(date);
            calendar.setTime(realDate);
        } catch (ParseException e) {
            throw  new RuntimeException("date parse false");
        }
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month + 1);
        return calendar;
    }
}
