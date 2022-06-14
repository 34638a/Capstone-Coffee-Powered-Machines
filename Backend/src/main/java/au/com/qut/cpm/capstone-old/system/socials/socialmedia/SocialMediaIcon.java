package au.com.qut.cpm.capstone.system.socials.socialmedia;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SocialMediaIcon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String socialName;
    private String iconClassStyle;
}
