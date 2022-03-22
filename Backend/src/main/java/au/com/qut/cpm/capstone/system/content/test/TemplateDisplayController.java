package au.com.qut.cpm.capstone.system.content.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateDisplayController {

    @GetMapping("/template")
    public String getIndexPage(Model model) {
        model.addAttribute("name", "Test User");
        return "fragments/template";
    }
}
