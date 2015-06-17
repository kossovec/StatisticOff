package ua.kossovec.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.kossovec.model.Ne;
import ua.kossovec.service.CsvParser;
import ua.kossovec.service.NeMapper;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NesDaoImpl implements NesDao {
  private final CsvParser csvParser;
  private static final int AXE_TYPE = 0;

  @Autowired
  public NesDaoImpl(CsvParser csvParser) {
    this.csvParser = csvParser;
  }

  @Override
  public Ne getNeByName(String neName) {
    List<String> linesFromCsv = csvParser.getLinesFromCsv();
    for (String line: linesFromCsv) {
      Ne ne = NeMapper.mapNe(line);
      if(ne.getName().equals(neName)) {
        return ne;
      }
    }
    return null;
  }

  @Override
  public List<Ne> getAllAxeNes() {
    List<String> linesFromCsv = csvParser.getLinesFromCsv();
    List<Ne> allNesList = new ArrayList<>();
    for (String line: linesFromCsv) {
      Ne ne = NeMapper.mapNe(line);
      if (ne.getType() == AXE_TYPE) {
        allNesList.add(ne);
      }
    }
    return allNesList;
  }

  public List<Ne> getAllNes() {
    List<String> linesFromCsv = csvParser.getLinesFromCsv();
    List<Ne> allNesList = new ArrayList<>();
    for (String line: linesFromCsv) {
      Ne ne = NeMapper.mapNe(line);
      allNesList.add(ne);
    }
    return allNesList;
  }
}
