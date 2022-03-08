package au.com.qut.cpm.capstone.objects.dataobjects.account;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserAccountPrivate {

    @Id
    private String userEmail;
    private String password;
}
