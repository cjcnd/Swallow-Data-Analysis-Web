package org.example.swllow_data_analysis.service.readfile;

import org.example.swllow_data_analysis.model.Swallow;

import java.io.File;
import java.util.List;

public interface FileService {
    String root_dir = new File("").getAbsolutePath();

    public List<Swallow> ReadFile(String fileName);
    public void MakeCsvFile(String fileName);
}
