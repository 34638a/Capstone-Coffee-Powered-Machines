package au.com.qut.cpm.capstone.controllers.dynamic.event;

import au.com.qut.cpm.capstone.objects.frontend.event.EventListing;
import au.com.qut.cpm.capstone.objects.utility.location.EventLocation;
import au.com.qut.cpm.capstone.objects.utility.socialmedia.SocialMedia;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Controller
public class EventListingController {

    public EventListing exampleListing = new EventListing();

    @PostConstruct
    public void setupComponent() {
        exampleListing.eventName = "Test Event";
        Calendar dateStart = new GregorianCalendar(2022, 9, 3, 10, 30);
        Calendar dateEnd = new GregorianCalendar(2022, 9, 7, 18, 30);
        exampleListing.eventStart = new Timestamp(dateStart.getTime().getTime());
        exampleListing.eventEnd = new Timestamp(dateEnd.getTime().getTime());
        exampleListing.socialMedia.add(new SocialMedia("fas fa-square-rss", "https://fontawesome.com/icons/square-rss?s=solid"));
        exampleListing.socialMedia.add(new SocialMedia("fa-brands fa-twitter-square", "https://fontawesome.com/icons/twitter-square?s=brands"));
        exampleListing.eventDescription = "Test Description";
        exampleListing.locationOfEvent = new EventLocation();
        exampleListing.locationOfEvent.setLocationType(EventLocation.LocationType.BOTH);
    }

    @GetMapping(value="/listing")
    public String getListingPage(Model model) {
        model.addAttribute("event", exampleListing);
        return "dynamic/event/eventListing";
    }
}
