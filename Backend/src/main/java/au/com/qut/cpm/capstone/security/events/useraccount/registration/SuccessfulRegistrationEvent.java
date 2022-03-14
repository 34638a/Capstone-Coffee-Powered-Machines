package au.com.qut.cpm.capstone.security.events.useraccount.registration;

import au.com.qut.cpm.capstone.security.entity.UserAccount;
import au.com.qut.cpm.capstone.security.entity.UserAccountEvent;
import org.springframework.context.ApplicationEvent;

public class SuccessfulRegistrationEvent extends ApplicationEvent {

    private final UserAccount userAccount;
    private final UserAccountEvent confirmationURL;

    public SuccessfulRegistrationEvent(UserAccount userAccount, UserAccountEvent confirmationURL) {
        super(userAccount);
        this.userAccount = userAccount;
        this.confirmationURL = confirmationURL;
    }
}
