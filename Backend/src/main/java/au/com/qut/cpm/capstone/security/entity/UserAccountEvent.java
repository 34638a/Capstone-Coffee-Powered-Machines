package au.com.qut.cpm.capstone.security.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class UserAccountEvent {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_account_id", nullable = false)
    private UserAccount userAccount;


    private String message;

    @Enumerated
    @Column(name = "type", nullable = false)
    private EventType eventType;

    public enum EventType {

        //Registration Events
        REGISTRATION_FIRST,
        REGISTRATION_CONFIRM,
        REGISTRATION_DUPLICATE_UNCONFIRMED,
        REGISTRATION_DUPLICATE_CONFIRMED,

    }
}
