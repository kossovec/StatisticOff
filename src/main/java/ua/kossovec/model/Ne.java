package ua.kossovec.model;

public class Ne {

  private final String name;
  private final String ip;
  private final String city;
  private final String filial;
  private final int type;

  public Ne(String neName, String ip, String city, String filial, int type) {
    this.name = neName;
    this.ip = ip;
    this.city = city;
    this.filial = filial;
    this.type = type;
  }

  public String getCity() {
    return city;
  }

  public String getFilial() {
    return filial;
  }

  public String getName() {
    return name;
  }

  public String getIp() {
    return ip;
  }

  public int getType() {
    return type;
  }
}
