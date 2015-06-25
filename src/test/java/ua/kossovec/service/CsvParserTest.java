package ua.kossovec.service;

import jcifs.smb.SmbFile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CsvParserTest {
    private static String FILE_DATE = "MGW 001;1;1;1;11.441;;;23;;;";
    private static String FILE_DATE_EMPTY = ";;;;;;;;;;";
    private InputStream inputStream = new ByteArrayInputStream(FILE_DATE.getBytes());
    private InputStream inputStreamWithEmptyLine = new ByteArrayInputStream(FILE_DATE_EMPTY.getBytes());

    @Mock
    SmbFile smbFile;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getLineFromFile() throws IOException {
        Mockito.when(smbFile.getInputStream()).thenReturn(inputStream);
        CsvParser parser = new CsvParser(smbFile, "NotExistFile");
        String lineFromList = null;
        List<String> lines = parser.getLinesFromCsv();
        for (String line : lines) {
            lineFromList = line;
        }
        Assert.assertEquals(lineFromList, FILE_DATE);
    }

    @Test
    public void skipLineIfLineIsEmpty() throws IOException {
        Mockito.when(smbFile.getInputStream()).thenReturn(inputStreamWithEmptyLine);
        CsvParser parser = new CsvParser(smbFile, "NotExistFile");
        List<String> lines = parser.getLinesFromCsv();
        Assert.assertTrue(lines.size() == 0);
    }
}
