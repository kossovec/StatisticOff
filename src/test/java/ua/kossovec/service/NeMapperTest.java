package ua.kossovec.service;

import org.junit.Assert;
import org.junit.Test;
import ua.kossovec.model.Ne;


public class NeMapperTest {
  private String lineFromCSV = "MGW 001;1;1;1;10.41.1.1;;;23;;;";

  @Test
  public void neMappingTest() {
    Ne ne = new Ne("MGW 001", "10.41.1.1", "1", "1", 1);
    Ne expectedNe = NeMapper.mapNe(lineFromCSV);
    Assert.assertEquals(ne.getName(), expectedNe.getName());
    Assert.assertEquals(ne.getIp(), expectedNe.getIp());
    Assert.assertEquals(ne.getCity(), expectedNe.getCity());
    Assert.assertEquals(ne.getFilial(), expectedNe.getFilial());
    Assert.assertEquals(ne.getType(), expectedNe.getType());
  }
}
