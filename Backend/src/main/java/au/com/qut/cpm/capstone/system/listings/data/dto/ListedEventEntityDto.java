package au.com.qut.cpm.capstone.system.listings.data.dto;

import au.com.qut.cpm.capstone.system.socials.socialmedia.SocialMedia;
import au.com.qut.cpm.capstone.utility.location.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListedEventEntityDto implements Serializable {

    private static final DateTimeFormatter fullDateFormat = DateTimeFormatter.ofPattern("EEEE d MMM yyyy hh:mm a");
    private static final DateTimeFormatter dayDateFormat = DateTimeFormatter.ofPattern("d");
    private static final DateTimeFormatter monthYearDateFormat = DateTimeFormatter.ofPattern("MMM yyyy");
    private static final DateTimeFormatter dayOfWeekDateFormat = DateTimeFormatter.ofPattern("EEEE");


    private String listingTitle;
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String description;
    private List<SocialMedia> socialMedia = new ArrayList<>();
    private UUID listedEventHostId;
    private Location location;


    public String getMonthYearStart() {
        return monthYearDateFormat.format(eventStart);
    }

    public String getDayStart() {
        return dayDateFormat.format(eventStart);
    }

    public String getStartEnd() {
        return fullDateFormat.format(eventStart) + " - " + fullDateFormat.format(eventEnd);
    }

    public String getDayOfWeek() {
        return dayOfWeekDateFormat.format(eventStart);
    }
}
