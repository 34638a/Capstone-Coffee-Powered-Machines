package au.com.qut.cpm.capstone.security.entity.listeners;

import au.com.qut.cpm.capstone.security.entity.UserAccount;
import au.com.qut.cpm.capstone.security.entity.UserAccountEvent;
import au.com.qut.cpm.capstone.security.events.useraccount.registration.AttemptRegistrationEvent;
import au.com.qut.cpm.capstone.security.events.useraccount.registration.SuccessfulRegistrationEvent;
import au.com.qut.cpm.capstone.security.events.useraccount.registration.UnsuccessfulRegistrationEvent;
import au.com.qut.cpm.capstone.security.model.UserAccountModel;
import au.com.qut.cpm.capstone.security.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationEventsListener {


    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @EventListener
    public void onAttemptRegistration(AttemptRegistrationEvent event) {

        UserAccount account = userAccountService.getIfExists(event.getUserAccountModel());
        if (account == null) {
            userAccountService.registerAccount(new UserAccountModel());
            return;
        }
        if (account.isVerifiedAccount()) {
            publisher.publishEvent(new UnsuccessfulRegistrationEvent(account,
                    new UserAccountEvent().setUserAccount(account).setEventType(UserAccountEvent.EventType.REGISTRATION_DUPLICATE_CONFIRMED)
            ));
        } else {

        }

        //publish event on success.
    }

    @EventListener
    public void onSuccessfulRegistration(SuccessfulRegistrationEvent event) {

        //Send Mail to the user welcoming them.
    }

    @EventListener
    public void onUnsuccessfulRegistration(UnsuccessfulRegistrationEvent event) {

        //
    }
}
