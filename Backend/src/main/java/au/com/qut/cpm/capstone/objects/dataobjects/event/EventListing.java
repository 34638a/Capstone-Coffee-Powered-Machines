package au.com.qut.cpm.capstone.objects.dataobjects.event;

import au.com.qut.cpm.capstone.utility.email.EmailAddress;
import au.com.qut.cpm.capstone.utility.location.MeetingPlace;
import au.com.qut.cpm.capstone.utility.phonenumber.ContactNumber;
import au.com.qut.cpm.capstone.system.socials.socialmedia.SocialMedia;

import java.sql.Timestamp;

public class EventListing {

    //Details tracked by the system.

    private int views;

    //Details Supplied by user when event is created / edited.
    private Timestamp dateTime;
    private EmailAddress eventContactEmail; //Optional
    private ContactNumber contactNumber; //Optional
    private SocialMedia[] socials; //Optional
    private MeetingPlace meetingPlace;
    private String postMessage;

}
