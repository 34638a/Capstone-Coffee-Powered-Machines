package au.com.qut.cpm.capstone.controllers.page.event;

import javax.persistence.*;

@Entity
public class EventHost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    //private EmailAddress hostAccountAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
