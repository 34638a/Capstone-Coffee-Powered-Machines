package au.com.qut.cpm.capstone.system.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContentController {

    @RequestMapping("/")
    public String homeEndpoint() {
        return "/standardcontent/home";
    }

    @RequestMapping("/legal")
    public String legalEndpoint() {
        return "/standardcontent/legal";
    }

    @RequestMapping("/contact")
    public String contactEndpoint() {
        return "/standardcontent/contact-us";
    }
}
