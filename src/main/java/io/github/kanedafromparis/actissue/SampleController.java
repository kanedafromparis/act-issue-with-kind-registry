package io.github.kanedafromparis.actissue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SampleController {
    @Value("${spring.application.name}")
    String applicationName;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("applicationName", applicationName);
        return "hello";
    }
}
