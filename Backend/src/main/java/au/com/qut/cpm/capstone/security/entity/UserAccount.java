package au.com.qut.cpm.capstone.security.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name="users")
public class UserAccount {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private boolean verifiedAccount = false;
    private boolean enabledAccount = false;
    @Column(length = 60)
    private String confirmCode;

    private String username;
    private String publicEmail;
    private String privateEmail; //Used to login

    public String role;

    @Column(length = 60)
    private String passwordHash;
}
