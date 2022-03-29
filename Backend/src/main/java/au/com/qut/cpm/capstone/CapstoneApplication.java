package au.com.qut.cpm.capstone;

import au.com.qut.cpm.capstone.system.email.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;

@SpringBootApplication
public class CapstoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapstoneApplication.class, args);
    }

    @Autowired
    private MailerService mailerService;

    @EventListener(ApplicationReadyEvent.class)
    public void onStart() throws MessagingException {
        System.out.println("Sending Mail Test");
        mailerService.sendEmailTemplate("bradsterrpi@gmail.com", "Test Mail");
        mailerService.sendEmail("bradsterrpi@gmail.com","This is not a template", "Not the Template");
        System.out.println("Tests sent successfully!");
        return;
    }
}
