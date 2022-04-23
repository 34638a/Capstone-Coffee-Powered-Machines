package au.com.qut.cpm.capstone.test;

import au.com.qut.cpm.capstone.security.account.object.database.UserAccount;
import au.com.qut.cpm.capstone.security.account.object.database.UserAccountRepository;
import lombok.extern.java.Log;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@Log
@Controller
public class AdministrationTestService {

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Value("${test.generate.users}")
    private boolean generateUsers;
    @Value("${test.generate.posts}")
    private boolean generatePosts;
    @Value("${test.generate.newsletters}")
    private boolean generateNewsletters;

    private List<UserAccount> testAccounts = new ArrayList<>();


    public void setupTestUsers() {
        log.info("Setup Test Users");

        for (int i = 0; i < 10; i++) {
            //Admin Account
            UserAccount userAccount = new UserAccount();
            userAccount.setEmail((RandomStringUtils.random(10, true, true) + "@test.com").toLowerCase(Locale.ROOT));
            userAccount.setPassword(passwordEncoder.encode("password"));
            userAccount.setRoles("ADMIN;USER;TEST");
            userAccount.setRegistered(true);
            userAccountRepository.save(userAccount);
            testAccounts.add(userAccount);
        }
    }

    public void setupTestPosts() {
        log.info("Setup Test Posts");
    }

    public void setupTestNewsletters() {
        log.info("Setup Test Newsletters");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void setupTests() {

        if (generateUsers) setupTestUsers();

    }


    @GetMapping("/tests/users")
    public ResponseEntity<String> getTestUsers() {
        log.info("Request for test users");
        if (generateUsers) {
            StringBuilder sb = new StringBuilder();
            for (UserAccount ua : testAccounts) {
                sb.append("{").append(ua.getEmail())
                        .append("\npassword: password")
                        .append("\nroles: ").append(ua.getRoles())
                        .append("} ");
            }
            return new ResponseEntity<>(sb.toString(),HttpStatus.I_AM_A_TEAPOT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
