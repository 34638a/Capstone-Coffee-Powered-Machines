package au.com.qut.cpm.capstone.controllers.standard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LegalPageController {
    @GetMapping("legal")
    public String getLegalPage(Model model) {
        model.addAttribute("name", "Test User");
        return "static/index";
    }
}
