package au.com.qut.cpm.capstone.dataobjects.event;

import au.com.qut.cpm.capstone.controllers.page.event.EventHost;
import au.com.qut.cpm.capstone.dataobjects.utility.email.EmailAddress;
import au.com.qut.cpm.capstone.dataobjects.socialmedia.SocialMedia;
import au.com.qut.cpm.capstone.dataobjects.utility.location.MeetingPlace;
import au.com.qut.cpm.capstone.dataobjects.utility.phonenumber.ContactNumber;

import java.sql.Timestamp;

public class EventListing {

    //Details tracked by the system.

    private EventHost hostOfEvent;
    private int views;

    //Details Supplied by user when event is created / edited.
    private Timestamp dateTime;
    private EmailAddress eventContactEmail; //Optional
    private ContactNumber contactNumber; //Optional
    private SocialMedia[] socials; //Optional
    private MeetingPlace meetingPlace;
    private String postMessage;

}
