package au.com.qut.cpm.capstone.objects.dataobjects.event;

import au.com.qut.cpm.capstone.objects.dataobjects.account.UserAccountPublic;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_host", nullable = false)
    private UserAccountPublic userAccountPublic;

    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;

    @Column(name = "last_updated", nullable = false)
    private Timestamp lastUpdated;

    @OneToMany(mappedBy = "event", orphanRemoval = true)
    private List<EventListingChanges> eventListingChanges = new ArrayList<>();


//Details Supplied by user when event is created / edited.
    //private Timestamp dateTime;
    //private EmailAddress eventContactEmail; //Optional
    //private ContactNumber contactNumber; //Optional
    //private SocialMedia[] socials; //Optional
    //private MeetingPlace meetingPlace;
    //private String postMessage;
}
