package org.example.swllow_data_analysis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class SwallowController {

    @GetMapping("/swallow")
    public String swallowController(Model model) {


        return "swallow.html";
    }
}
