package org.example.swllow_data_analysis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class MappingController {

    @GetMapping("/map/{id}")
    public String mappingController(Model model, @PathVariable Long id) {


        return "map.html";
    }
}
