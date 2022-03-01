package au.com.qut.cpm.capstone.dataobjects.event;

import au.com.qut.cpm.capstone.controllers.page.event.EventHost;
import au.com.qut.cpm.capstone.dataobjects.utility.email.EmailAddress;
import au.com.qut.cpm.capstone.dataobjects.socialmedia.SocialMedia;
import au.com.qut.cpm.capstone.dataobjects.utility.phonenumber.ContactNumber;

import java.sql.Timestamp;

public class ListedEvent {

    private EventHost hostOfEvent;
    private Timestamp dateTime;
    private EmailAddress eventContactEmail; //Optional
    private ContactNumber contactNumber; //Optional

    //Social Media
    private SocialMedia[] socials; //Optional
    private MeetingPlace meetingPlace;

    //Detail Supplied
    private String postMessage;

}
