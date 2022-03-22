package au.com.qut.cpm.capstone.system.listings.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListedEventsCalendarController {


    @GetMapping
    public String getIndexPage(Model model) {
        model.addAttribute("name", "Test User");
        return "static/index";
    }
}
