package au.com.qut.cpm.capstone.system.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailerService {

    @Autowired
    private JavaMailSender mailSender; //Ignore issue, it's not real.

    public void sendEmailNewsletter() {

    }

    public void sendEmail(String recipient, String emailBody, String emailSubject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setText(emailBody);
        simpleMailMessage.setSubject(emailSubject);
        mailSender.send(simpleMailMessage);
    }
}
