package au.com.qut.cpm.capstone.dataobjects.event;

import au.com.qut.cpm.capstone.controllers.page.event.EventHost;
import au.com.qut.cpm.capstone.dataobjects.email.EmailAddress;
import au.com.qut.cpm.capstone.dataobjects.socialmedia.SocialMedia;

import java.sql.Timestamp;

public class ListedEvent {

    private EventHost hostOfEvent;
    private EmailAddress eventContactEmail;
    private Timestamp dateTime;
    private String contactNumber;
    private SocialMedia[] socials;
    private StreetAddress streetAddress;
    private String postMessage;

}
