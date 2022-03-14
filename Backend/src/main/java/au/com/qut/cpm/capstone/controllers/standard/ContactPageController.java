package au.com.qut.cpm.capstone.controllers.standard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactPageController {
    @GetMapping("contact")
    public String getIndexPage(Model model) {
        model.addAttribute("name", "Test User");
        return "static/contact";
    }
}
