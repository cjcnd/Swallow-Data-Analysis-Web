package org.example.swllow_data_analysis.controller;

import org.example.swllow_data_analysis.model.Swallow;
import org.example.swllow_data_analysis.model.SwallowTable;
import org.example.swllow_data_analysis.repository.SwallowRepository;
import org.example.swllow_data_analysis.repository.SwallowTableRepository;
import org.example.swllow_data_analysis.service.FileService;
import org.example.swllow_data_analysis.service.FileInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Controller
public class UploadController {

    private final FileInputService fileInputService;
    private final FileService fileService;
    private final SwallowRepository swallowRepository;
    private final SwallowTableRepository swallowTableRepository;

    @Autowired
    public UploadController(FileInputService fileInputService,
                            FileService fileService,
                            SwallowRepository swallowRepository,
                            SwallowTableRepository swallowTableRepository)
    {
        this.fileInputService = fileInputService;
        this.fileService = fileService;
        this.swallowRepository = swallowRepository;
        this.swallowTableRepository = swallowTableRepository;
    }

    @GetMapping("/upload")
    public String GetUploadController(Model model) {
        return "upload.html";
    }

    @PostMapping("/upload")
    public String PostUploadController(Model model, @RequestParam("file")MultipartFile file) {
        String fileName = fileInputService.store(file);
        List<Swallow> swallowList = fileService.ReadFile(fileName);
        swallowRepository.saveAll(swallowList);
        swallowTableRepository.save(new SwallowTable(new Date(), swallowList));

        return "redirect:/";
    }

}
