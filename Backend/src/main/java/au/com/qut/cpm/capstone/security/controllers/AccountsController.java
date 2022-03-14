package au.com.qut.cpm.capstone.security.controllers;

import au.com.qut.cpm.capstone.security.events.useraccount.registration.AttemptRegistrationEvent;
import au.com.qut.cpm.capstone.security.model.UserAccountModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/accounts")
public class AccountsController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserAccountModel userAccountModel) {
        publisher.publishEvent(new AttemptRegistrationEvent(userAccountModel));
        return "Success";
    }
}
