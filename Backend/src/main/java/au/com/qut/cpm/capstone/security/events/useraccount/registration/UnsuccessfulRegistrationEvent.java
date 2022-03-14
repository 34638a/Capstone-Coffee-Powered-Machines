package au.com.qut.cpm.capstone.security.events.useraccount.registration;

import au.com.qut.cpm.capstone.security.entity.UserAccount;
import au.com.qut.cpm.capstone.security.entity.UserAccountEvent;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UnsuccessfulRegistrationEvent extends ApplicationEvent {

    private final UserAccount userAccount;
    private final UserAccountEvent generatedEvent;

    public UnsuccessfulRegistrationEvent(UserAccount userAccount, UserAccountEvent generatedEvent) {
        super(userAccount);
        this.userAccount = userAccount;
        this.generatedEvent = generatedEvent;
    }
}
