package au.com.qut.cpm.capstone.dataobjects.account;

import au.com.qut.cpm.capstone.dataobjects.utility.email.EmailAddress;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserAccount {

    @Id
    private EmailAddress userEmail;
    private String password;
}
