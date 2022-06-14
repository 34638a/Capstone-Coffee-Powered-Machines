package au.com.qut.cpm.capstone.system.controllers;

import au.com.qut.cpm.capstone.system.publication.Publication;
import au.com.qut.cpm.capstone.system.publication.database.PublicationRepository;
import au.com.qut.cpm.capstone.system.publication.publications.listing.Listing;
import au.com.qut.cpm.capstone.system.publication.publications.listing.ListingNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ListingController {

    private final PublicationRepository repository;

    public ListingController(PublicationRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("listing")
    public String viewListings( Model model) {
        return "listings/feed";
    }

    @RequestMapping("listing/{id}")
    public String viewSpecificListing(@PathVariable String id, Model model) {
        Optional<Publication> publication = repository.findById(UUID.fromString(id));
        if (publication.isEmpty()) throw new ListingNotFoundException();

        Listing listing;
        try {
            listing = publication.get().getPublicationContent().getJsonResourceFileAsObject(Listing.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("listing", listing);
        return "listings/listing";
    }
}
