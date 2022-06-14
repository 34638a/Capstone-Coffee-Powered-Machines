package au.com.qut.cpm.capstone.system.listings.data.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListedEventEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    private String listingTitle;
    /**
     * Timestamps are in UTC. Convert to local time in browser.
     */
    private LocalDateTime eventStart, eventEnd;

    //Tracking Information
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    private String description;

    /**
     * Encoded String using SPACE %20 between entries.<br/>
     * Social Media Glyphs are marked using a Java.Long number.<br/>
     * Format layout &lt;number#url&gt; &lt;number#url&gt; ... &lt;number#url&gt;
     */
    private String socialMedia;

    @ManyToOne(optional = false)
    @JoinColumn(name = "eventhost", nullable = false)
    private ListedEventHost listedEventHost;

    private String location64;
}
