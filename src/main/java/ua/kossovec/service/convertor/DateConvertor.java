package ua.kossovec.service.convertor;

import java.util.Calendar;

public class DateConvertor {
  public static String covertToSTMMPFormat(Calendar calendar) {
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    String sMonth = (month < 10) ? "0" + month : "" + month;
    String sDay = (day < 10) ? "0" + day : "" + day;
    if (minute != 0) {
      hour += 1;
    }

    String sHour = (hour < 10) ? "0" + hour : "" + hour;
    return year + sMonth + sDay + sHour + "00";
  }
}
