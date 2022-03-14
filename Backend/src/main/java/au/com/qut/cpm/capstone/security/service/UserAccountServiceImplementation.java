package au.com.qut.cpm.capstone.security.service;

import au.com.qut.cpm.capstone.security.entity.UserAccount;
import au.com.qut.cpm.capstone.security.entity.UserAccountEvent;
import au.com.qut.cpm.capstone.security.events.useraccount.registration.SuccessfulRegistrationEvent;
import au.com.qut.cpm.capstone.security.model.UserAccountModel;
import au.com.qut.cpm.capstone.security.repository.UserAccountEventRepository;
import au.com.qut.cpm.capstone.security.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountServiceImplementation implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private UserAccountEventRepository userAccountEventRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public UserAccount getIfExists(UserAccountModel userAccountModel) {
        List<UserAccount> accounts = userAccountRepository.findUserAccountsByPrivateEmail(userAccountModel.getEmail());
        if (accounts.size() == 0) return null;
        return accounts.get(0);
    }

    @Override
    public UserAccount registerAccount(UserAccountModel userAccountModel) {
        UserAccount newUserAccount = new UserAccount();
        newUserAccount.setUsername(userAccountModel.getUsername());
        newUserAccount.setPrivateEmail(userAccountModel.getEmail());
        newUserAccount.setRole("USER");
        newUserAccount.setPasswordHash(passwordEncoder.encode(userAccountModel.getPassword()));

        userAccountRepository.save(newUserAccount);

        publisher.publishEvent(new SuccessfulRegistrationEvent(
                newUserAccount,
                new UserAccountEvent()
                        .setUserAccount(newUserAccount)
                        .setEventType(UserAccountEvent.EventType.REGISTRATION_FIRST)
                        .setMessage("TODO: URL")
        ));

        return newUserAccount;
    }
}
