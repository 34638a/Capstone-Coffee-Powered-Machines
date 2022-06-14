package au.com.qut.cpm.capstone.system.publication.publications.listing;

import au.com.qut.cpm.capstone.system.publication.PublicationContentReference;
import au.com.qut.cpm.capstone.utility.SocialMediaLink;
import au.com.qut.cpm.capstone.utility.location.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class Listing extends PublicationContentReference {

    private Timestamp timeOfEvent;
    private TimeZone timeZone;
    private String title;
    private String description;
    private List<SocialMediaLink> socialMediaLinks;
    private Location location;

    private List<ListingHost> additionalHosts;
    private UUID poster;
}
