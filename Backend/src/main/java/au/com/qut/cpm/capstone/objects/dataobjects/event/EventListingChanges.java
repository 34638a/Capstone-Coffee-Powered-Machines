package au.com.qut.cpm.capstone.objects.dataobjects.event;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class EventListingChanges {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "change_time", nullable = false)
    private Timestamp changeTime;

    @Column(name = "changed_value", nullable = false)
    private String changedValue;

    @Column(name = "changed_field", nullable = false)
    private String changedField;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
