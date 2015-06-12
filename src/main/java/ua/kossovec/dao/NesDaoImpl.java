package ua.kossovec.dao;

import jcifs.smb.SmbFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.kossovec.model.Ne;

import java.util.List;
@Repository
public class NesDaoImpl implements NesDao {
  SmbFile smbFile;

  @Autowired
  public NesDaoImpl(SmbFile smbFile) {
   this.smbFile = smbFile;
  }

  public List<Ne> getNes() {

    return null;
  }
}
