package au.com.qut.cpm.capstone.system.controllers;

import au.com.qut.cpm.capstone.system.publication.ContentPublicationService;
import au.com.qut.cpm.capstone.system.publication.Publication;
import au.com.qut.cpm.capstone.system.publication.publications.listing.Listing;
import au.com.qut.cpm.capstone.system.publication.publications.listing.ListingNotFoundException;
import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.rss.Channel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.UUID;

@Controller
public class ListingController {

    @RequestMapping("listing")
    public String viewListings( Model model) {
        return "listings/feed";
    }

    @RequestMapping("listing/{id}")
    public String viewSpecificListing(@PathVariable String id, Model model) {
        Publication publication;
        try {
            publication = ContentPublicationService.getPublication(UUID.fromString(id));
        } catch (Exception e) {
            throw new ListingNotFoundException();
        }

        Listing listing;
        try {
            listing = publication.getPublicationContent().getJsonResourceFileAsObject(Listing.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("listing", listing);
        return "listings/listing";
    }

    @RequestMapping("listing/{id}/rss")
    public Channel getListingRSSFeed(@PathVariable String id) {
        Channel channel = new Channel();
        channel.setFeedType("rss_2.0");
        channel.setTitle("Mining Events Info Feed");
        channel.setDescription("Articles posted to the listing feed.");
        channel.setLink("https://www.miningevents.info");
        channel.setUri("https://www.miningevents.info");
        channel.setGenerator("In House Programming");
        //Like more Entries here about different new topics
        return channel;
    }

    @RequestMapping("listing/{id}/atom")
    public Feed getListingAtomFeed(@PathVariable String id) {
        Feed feed = new Feed();
        feed.setFeedType("atom_1.0");
        feed.setTitle("Mining Events Info Feed");
        feed.setId("https://www.miningevents.info");

        Content subtitle = new Content();
        subtitle.setType("text/plain");
        subtitle.setValue("Updates to the listing");
        feed.setSubtitle(subtitle);

        return feed;
    }
}
