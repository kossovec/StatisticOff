package ua.kossovec.service;
import ua.kossovec.model.Ne;

public class NeMapper {

  private static final String LINE_SEPARATOR = ";";
  private static final int NE_NAME_INDEX = 0;
  private static final int NE_IP_INDEX = 4;
  private static final int NE_CITY_INDEX = 2;
  private static final int NE_FILIAL_INDEX = 1;
  private static final int NE_TYPE_INDEX = 3;

  public static Ne mapNe(String lineFromCSV) {
    String[] arrayFromLine = lineFromCSV.split(LINE_SEPARATOR);
    String neName = arrayFromLine[NE_NAME_INDEX].trim();
    String ip = arrayFromLine[NE_IP_INDEX].trim();
    String city = arrayFromLine[NE_CITY_INDEX].trim();
    String filial = arrayFromLine[NE_FILIAL_INDEX].trim();
    int type = Integer.parseInt(arrayFromLine[NE_TYPE_INDEX].trim());
    return new Ne(neName, ip, city, filial,type);
  }
}
