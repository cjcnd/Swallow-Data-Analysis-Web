package org.swallow.swallow_data_analysis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.swallow.swallow_data_analysis.model.SwallowTable;
import org.swallow.swallow_data_analysis.repository.SwallowRepository;
import org.swallow.swallow_data_analysis.repository.SwallowTableRepository;
import org.swallow.swallow_data_analysis.service.FileConvertSystem;
import org.swallow.swallow_data_analysis.service.FileInputSystem;

@RestController
@Slf4j
public class MainController {

    private final FileInputSystem fileInputSystem;
    private final FileConvertSystem fileConvertSystem;
    private final SwallowTableRepository swallowTableRepository;
    private final SwallowRepository swallowRepository;

    @Autowired
    public MainController(
            FileInputSystem fileInputSystem,
            FileConvertSystem fileConvertSystem,
            SwallowTableRepository swallowTableRepository,
            SwallowRepository swallowRepository) {
        this.fileConvertSystem = fileConvertSystem;
        this.fileInputSystem = fileInputSystem;
        this.swallowRepository = swallowRepository;
        this.swallowTableRepository = swallowTableRepository;
    }

    @GetMapping("/")
    public String check() {
        return "hello";
    }

    @PostMapping("/swallow")
    public ResponseEntity<Object> swallowRegisterController(@RequestParam("file") MultipartFile file) {
        log.info(file.getOriginalFilename());

        return ResponseEntity.ok("ok");
    }

    @GetMapping("/swallow/{id}")
    public SwallowTable swallowFindOneController(@PathVariable String id) {
        return new SwallowTable();
    }
}