package au.com.qut.cpm.capstone.system.listings.data.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class ListedEventHost {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;


}
