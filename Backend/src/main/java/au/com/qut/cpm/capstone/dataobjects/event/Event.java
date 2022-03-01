package au.com.qut.cpm.capstone.dataobjects.event;

import au.com.qut.cpm.capstone.dataobjects.account.UserAccount;

import java.util.ArrayList;
import java.util.List;

public class Event {

    private UserAccount eventHost;
    private EventListing liveEventDetails;
    private List<EventListingHistory> modificationHistory = new ArrayList<>();
}
