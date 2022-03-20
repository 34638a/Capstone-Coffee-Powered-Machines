package au.com.qut.cpm.capstone.system.listings.controllers;

import au.com.qut.cpm.capstone.objects.frontend.event.EventListing;
import au.com.qut.cpm.capstone.utility.location.EventLocation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Controller
@RequestMapping("/events")
public class ListedEventController {


    public EventListing exampleListing = new EventListing();

    @PostConstruct
    public void setupComponent() {
        exampleListing.eventName = "Test Event";
        Calendar dateStart = new GregorianCalendar(2022, 9, 3, 10, 30);
        Calendar dateEnd = new GregorianCalendar(2022, 9, 7, 18, 30);
        exampleListing.eventStart = new Timestamp(dateStart.getTime().getTime());
        exampleListing.eventEnd = new Timestamp(dateEnd.getTime().getTime());
        //exampleListing.socialMedia.add(new SocialMedia("fas fa-square-rss", "https://fontawesome.com/icons/square-rss?s=solid"));
        //exampleListing.socialMedia.add(new SocialMedia("fa-brands fa-twitter-square", "https://fontawesome.com/icons/twitter-square?s=brands"));
        exampleListing.eventDescription = "Test Description, Hi James";
        exampleListing.locationOfEvent = new EventLocation();
        exampleListing.locationOfEvent.setLocationType(EventLocation.LocationType.BOTH);
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

    public String getEventAdminPage() {
        return "";
    }
}
