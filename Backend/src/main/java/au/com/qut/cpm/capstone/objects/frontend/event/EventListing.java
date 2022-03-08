package au.com.qut.cpm.capstone.objects.frontend.event;

import au.com.qut.cpm.capstone.objects.utility.location.EventLocation;
import au.com.qut.cpm.capstone.objects.utility.socialmedia.SocialMedia;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@JsonSerialize(using = EventListingJson.EventListingSerializer.class)
@JsonDeserialize(using = EventListingJson.EventListingDeserializer.class)
public class EventListing {

    private static final SimpleDateFormat fullDateFormat = new SimpleDateFormat("EEEE d MMM yyyy hh:mm a");
    private static final SimpleDateFormat dayDateFormat = new SimpleDateFormat("d");
    private static final SimpleDateFormat monthYearDateFormat = new SimpleDateFormat("MMM yyyy");
    private static final SimpleDateFormat dayOfWeekDateFormat = new SimpleDateFormat("EEEE");

    public String eventName;
    /**
     * Timestamps are in UTC. Convert to local time in browser.
     */
    public Timestamp eventStart, eventEnd;

    public EventLocation locationOfEvent;

    public final List<SocialMedia> socialMedia = new ArrayList<>();

    public String eventDescription;

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
