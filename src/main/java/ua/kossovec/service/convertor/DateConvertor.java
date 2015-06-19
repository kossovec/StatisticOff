package ua.kossovec.service.convertor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConvertor {
    public static String covertToSTMMPFormat(Calendar calendar) {
        Date endTime = calendar.getTime();
        SimpleDateFormat dateFormatYMD = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dateFormatHH = new SimpleDateFormat("HH");
        String firstPartOfDate = dateFormatYMD.format(endTime);
        int hour = Integer.parseInt(dateFormatHH.format(endTime)) + 1;
        return firstPartOfDate + hour + "00";
    }
}
