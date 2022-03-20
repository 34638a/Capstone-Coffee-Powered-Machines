package au.com.qut.cpm.capstone.system.listings.data.database;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class ListedEventHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "for_event_id")
    private ListedEventEntity forEvent;

    @Column(name = "change_time", nullable = false)
    private Timestamp changeTime;

    @Column(name = "changed_value", nullable = false)
    private String changedValue;

    @Column(name = "changed_field", nullable = false)
    private String changedField;

    public ListedEventEntity getForEvent() {
        return forEvent;
    }

}