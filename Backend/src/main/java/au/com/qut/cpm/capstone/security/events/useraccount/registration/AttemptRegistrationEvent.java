package au.com.qut.cpm.capstone.security.events.useraccount.registration;

import au.com.qut.cpm.capstone.security.model.UserAccountModel;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AttemptRegistrationEvent extends ApplicationEvent {

    private final UserAccountModel userAccountModel;

    public AttemptRegistrationEvent(UserAccountModel userAccountModel) {
        super(userAccountModel);
        this.userAccountModel = userAccountModel;
    }
}
