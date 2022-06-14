package au.com.qut.cpm.capstone.system.listings.controllers;

import au.com.qut.cpm.capstone.system.listings.data.dto.ListedEventEntityDto;
import au.com.qut.cpm.capstone.system.socials.socialmedia.SocialMedia;
import au.com.qut.cpm.capstone.system.socials.socialmedia.SocialMediaIcon;
import au.com.qut.cpm.capstone2.utility.location.Location;
import com.github.javafaker.Faker;
import com.github.javafaker.Lorem;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Image;
import com.rometools.rome.feed.rss.Item;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;

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
    public String getEventPage(@PathVariable String event, Model model, HttpServletRequest request) {
        model.addAttribute("event", exampleListing);
        model.addAttribute("authorised", request.isUserInRole("test"));
        return "dynamic/event/eventListing";
    }

    @GetMapping("/{event}/changes")
    public String getEventPageChanges(@PathVariable String event, Model model) {
        model.addAttribute("event", exampleListing);
        return "dynamic/event/eventListing";
    }

    @GetMapping("/{event}/rss")
    public ResponseEntity<Channel> getEventPageRSS(@PathVariable String event, Model model) {
        model.addAttribute("event", exampleListing);

        ListedEventEntityDto eventEntityDto = new ListedEventEntityDto();
        eventEntityDto.setListingTitle("This is a Test");

        Channel channel = new Channel();
        channel.setFeedType("rss_2.0");
        channel.setTitle("Mining Event Feed: " + eventEntityDto.getListingTitle());
        channel.setDescription("Updates from the subscribed feed.");
        channel.setLink("https://miningevents.info");
        channel.setUri("https://miningevents.info");
        channel.setGenerator("Dynamic Feed");

        Image image = new Image();
        image.setUrl("https://media-exp1.licdn.com/dms/image/C510BAQEVdbwr1udxow/company-logo_200_200/0/1543815641851?e=2147483647&v=beta&t=bigcmx6n-LVRrxBEuWabM6b1CuanXyyDjxh05gXpf0s");
        image.setTitle("Orefox Logo");
        image.setHeight(200);
        image.setWidth(200);
        channel.setImage(image);

        Date postDate = new Date();
        channel.setPubDate(postDate);

        Item item = new Item();
        item.setAuthor("Mining Event: " + eventEntityDto.getListingTitle());
        item.setLink("https://miningevents.info/events/" + event);
        item.setTitle(eventEntityDto.getListingTitle());
        item.setUri("https://miningevents.info/events/" + event);

        com.rometools.rome.feed.rss.Category category = new com.rometools.rome.feed.rss.Category();
        category.setValue("Updates");
        item.setCategories(Collections.singletonList(category));

        Description descr = new Description();
        descr.setValue(
                "The post <a rel=\"nofollow\" href=\"https://miningevents.info/\">Spring CORS Configuration Examples</a> appeared first on <a rel=\"nofollow\" href=\"https://howtodoinjava.com\">HowToDoInJava</a>.");
        item.setDescription(descr);
        item.setPubDate(postDate);

        channel.setItems(Collections.singletonList(item));
        //Like more Entries here about different new topics
        return new ResponseEntity<>(channel, HttpStatus.OK);
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
