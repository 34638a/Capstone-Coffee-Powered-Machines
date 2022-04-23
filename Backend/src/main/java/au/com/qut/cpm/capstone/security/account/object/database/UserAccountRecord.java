package au.com.qut.cpm.capstone.security.account.object.database;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class UserAccountRecord {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID recordID;

    @ManyToOne(optional = false)
    @JoinColumn(name = "forAccount", nullable = false)
    private UserAccount userAccount;

    private String dataString;
}
