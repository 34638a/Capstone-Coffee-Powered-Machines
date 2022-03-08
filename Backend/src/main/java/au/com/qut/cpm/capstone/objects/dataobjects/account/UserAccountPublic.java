package au.com.qut.cpm.capstone.objects.dataobjects.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class UserAccountPublic {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    public UUID getId() {
        return id;
    }
}
