package org.example.swllow_data_analysis.service;

import org.example.swllow_data_analysis.model.Swallow;

import java.io.File;
import java.util.List;

public interface FileService {
    String root_dir = new File("").getAbsolutePath();

    List<Swallow> ReadFile(String fileName);
    void MakeCsvFile(String fileName);
}
