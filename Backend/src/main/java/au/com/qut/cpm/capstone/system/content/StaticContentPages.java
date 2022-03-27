package au.com.qut.cpm.capstone.system.content;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StaticContentPages {

    @GetMapping
    public String getIndexPage(Model model, HttpServletRequest request) {
        model.addAttribute("name", "Test User");

        System.out.println(request.getUserPrincipal());
        if (request.isUserInRole("test")) {
            System.out.println("TEST CONFIRMED");
            return "static/legal";
        }
        return "static/index";
    }

    @GetMapping("legal")
    public String getLegalPage(Model model) {
        model.addAttribute("name", "Test User");
        return "static/legal";
    }

    @GetMapping("about-us")
    public String getAboutUsPage(Model model) {
        model.addAttribute("name", "Test User");
        return "static/index";
    }

    @GetMapping("contact")
    public String getContactPage(Model model) {
        model.addAttribute("name", "Test User");
        return "static/contact";
    }
}
