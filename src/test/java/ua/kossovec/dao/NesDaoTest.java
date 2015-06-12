package ua.kossovec.dao;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import ua.kossovec.model.Ne;

import java.util.List;
@ContextConfiguration("/test-config.xml")
public class NesDaoTest {

  @Autowired
  NesDao nesDao;

  @Autowired
  SmbFile smbFile;

  @Test
  public void testAccessesToRemoteServerWhereDataIs() {
    try {
      Assert.assertTrue(smbFile.exists());
    } catch (SmbException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void parseFile() {
    List<Ne> nes = nesDao.getNes();

  }

}
