package au.com.qut.cpm.capstone.controllers.dynamic.event;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventListingsController {


    @GetMapping(value="/events")
    public String getListingPage(Model model) {
        //model.addAttribute("event", exampleListing);
        return "index";
    }
}
