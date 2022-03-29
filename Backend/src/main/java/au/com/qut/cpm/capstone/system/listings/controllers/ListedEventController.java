package au.com.qut.cpm.capstone.system.listings.controllers;

import au.com.qut.cpm.capstone.system.listings.data.dto.ListedEventEntityDto;
import au.com.qut.cpm.capstone.system.socials.socialmedia.SocialMedia;
import au.com.qut.cpm.capstone.system.socials.socialmedia.SocialMediaIcon;
import au.com.qut.cpm.capstone.utility.location.Location;
import com.github.javafaker.Faker;
import com.github.javafaker.Lorem;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/events")
public class ListedEventController {

    private static final Lorem lorem = Faker.instance().lorem();
    public ListedEventEntityDto exampleListing = new ListedEventEntityDto();

    @PostConstruct
    public void setupComponent() {
        exampleListing.setListingTitle("Test Event");
        exampleListing.setEventStart(LocalDateTime.of(2022, 9, 3, 10, 30));
        exampleListing.setEventEnd(LocalDateTime.of(2022, 9, 7, 18, 30));
        exampleListing.getSocialMedia().add(new SocialMedia().setSocialMediaIcon(new SocialMediaIcon().setSocialName("RSS").setIconClassStyle("fas fa-square-rss")).setUrl("https://fontawesome.com/icons/square-rss?s=solid"));
        exampleListing.getSocialMedia().add(new SocialMedia().setSocialMediaIcon(new SocialMediaIcon().setSocialName("Twitter").setIconClassStyle("fa-brands fa-twitter-square")).setUrl("https://fontawesome.com/icons/twitter-square?s=brands"));
        exampleListing.setDescription(Faker.instance().lorem().paragraph(28));
        exampleListing.setLocation(new Location());
        exampleListing.getLocation().setLocationType(Location.LocationType.BOTH);
    }


    @GetMapping
    public String getEventsListPage() {
        return "dynamic/event/events";
    }

    @GetMapping("/{event}")
    public String getEventPage(@PathVariable String event, Model model) {
        model.addAttribute("event", exampleListing);
        return "dynamic/event/eventListing";
    }

    @GetMapping("/{event}/changes")
    public String getEventPageChanges(@PathVariable String event, Model model) {
        model.addAttribute("event", exampleListing);
        return "dynamic/event/eventListing";
    }

    @PostMapping("/{event}/update")
    public ResponseEntity<String> updateEventLogs(@PathVariable String event, HttpRequest request) {


        System.out.println("POST: " + event);

        return new ResponseEntity<String>("Test", HttpStatus.OK);
    }


    public String getEventAdminPage() {
        return "";
    }


    @GetMapping("/calendar")
    public String getCalendarPage(Model model) {
        model.addAttribute("name", "Test User");
        return "static/calendar";
    }
}
