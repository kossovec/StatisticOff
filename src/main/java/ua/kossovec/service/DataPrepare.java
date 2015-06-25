package ua.kossovec.service;

import jcifs.smb.SmbFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;

@Component
public class DataPrepare {
    @Autowired
    private SmbFile smbFile;
    @Autowired
    private String name;

    @PostConstruct
    public void init() throws Exception {
        File file = new File(name);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        BufferedReader reader = new BufferedReader(new InputStreamReader(smbFile.getInputStream()));
        char[] buff = new char[1024];
        while (reader.read(buff) != -1) {
            writer.write(buff);
        }
        writer.close();
        reader.close();
    }
}
