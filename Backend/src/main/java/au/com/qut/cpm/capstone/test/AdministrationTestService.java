package au.com.qut.cpm.capstone.test;

import au.com.qut.cpm.capstone.security.account.object.database.UserAccount;
import au.com.qut.cpm.capstone.security.account.object.database.UserAccountRepository;
import au.com.qut.cpm.capstone.system.publication.ContentPublicationService;
import au.com.qut.cpm.capstone.system.publication.Publication;
import au.com.qut.cpm.capstone.system.publication.publications.listing.Listing;
import au.com.qut.cpm.capstone.utility.SocialMediaLink;
import au.com.qut.cpm.capstone.utility.location.Location;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
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

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

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
            log.info(userAccount.toString());
        }
    }

    @SneakyThrows
    public void setupTestPosts() {
        log.info("Setup Test Posts");

        log.info("Check \"" + ContentPublicationService.contentStoragePath + "\" for files");
        Publication publication = new Publication();
        ContentPublicationService.savePublication(publication).saveNewContentFile( new Listing(
                Timestamp.from(Instant.now()),
                TimeZone.getDefault(),
                "Test Event Title",
                "Test Event Description",
                List.of(new SocialMediaLink("fa-linkedin", "https://www.linkedin.com")),
                new Location(
                        "",
                        "1 test drv",
                        "",
                        "Test",
                        "TEST",
                        "1111",
                        "Test Country"
                ),
                new ArrayList<>(),
                testAccounts.get(0).getId()
        ));
        publication = ContentPublicationService.getPublication(publication.getId());
        System.out.println(publication.getId());
        System.out.println(publication.getId());
        System.out.println(publication.getId());
        Listing listing = publication.getPublicationContent().getJsonResourceFileAsObject(Listing.class);
        System.out.println(listing);
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(listing));
    }

    public void setupTestNewsletters() {
        log.info("Setup Test Newsletters");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void setupTests() {

        if (generateUsers) setupTestUsers();
        if (generatePosts) setupTestPosts();
    }

    @GetMapping("/tests/template")
    public String getTemplateFile() {
        return "template";
    }
    @GetMapping("/tests/template/**")
    public String getTemplateFilePath(HttpServletRequest request) {
        String file = request.getRequestURI()
                .split(request.getContextPath() + "/tests/template/")[1];
        System.out.println(file);
        return file;
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
