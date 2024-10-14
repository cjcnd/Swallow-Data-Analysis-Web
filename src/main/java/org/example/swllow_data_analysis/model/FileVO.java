package org.example.swllow_data_analysis.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileVO {
    private String fileName;
    private MultipartFile uploadFile;
}
