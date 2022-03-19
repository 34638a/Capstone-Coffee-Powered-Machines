package au.com.qut.cpm.capstone.system.content;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticContentPages {

    @GetMapping
    public String getIndexPage(Model model) {
        model.addAttribute("name", "Test User");
        return "static/index";
    }

    @GetMapping("legal")
    public String getLegalPage(Model model) {
        model.addAttribute("name", "Test User");
        return "static/index";
    }

    @GetMapping("contact")
    public String getContactPage(Model model) {
        model.addAttribute("name", "Test User");
        return "static/contact";
    }
}
