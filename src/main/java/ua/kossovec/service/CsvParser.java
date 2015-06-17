package ua.kossovec.service;

import jcifs.smb.SmbFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class CsvParser {

  private InputStream inputStream;

  @Autowired
  public CsvParser(SmbFile smbFile) {
    try {
      inputStream = smbFile.getInputStream();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public List<String> getLinesFromCsv() {
    List<String> lines = new ArrayList<>();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(inputStream));
      String line = null;
      while ((line = reader.readLine()) != null) {
        if(line.startsWith("#")) {
          continue;
        }
        lines.add(line);
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return lines;
  }
}
