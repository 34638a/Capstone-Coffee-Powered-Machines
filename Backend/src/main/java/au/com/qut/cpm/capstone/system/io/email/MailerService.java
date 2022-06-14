package au.com.qut.cpm.capstone.system.io.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailerService {

    @Value("${spring.mail.sender}")
    private String sender;
    @Value("${spring.mail.username}")
    private String senderEmail;
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    public MailerService(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(EmailWrapper emailWrapper) throws MessagingException {

        String process = templateEngine.process(emailWrapper.getTemplate(), emailWrapper.getContext());
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom(sender + " <" + senderEmail + ">");
        helper.setSubject(emailWrapper.getSubject());
        helper.setText(process, true);
        if (emailWrapper.isToIndividuals()) {
            for (String sendTo : emailWrapper.getSendTo()) {
                helper.setTo(sendTo);
                javaMailSender.send(mimeMessage);
            }
        } else {
            for (String sendTo : emailWrapper.getSendTo()) {
            }
            helper.setTo(String.join("; ", emailWrapper.getSendTo()));
            javaMailSender.send(mimeMessage);
        }
    }

    public void sendEmailTemplate(String recipient, String emailSubject) throws MessagingException {
        Context context = new Context();
        context.setVariable("user", recipient);

        String process = templateEngine.process("emails/newsletter", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(emailSubject);
        helper.setText(process, true);
        helper.setTo(recipient);
        javaMailSender.send(mimeMessage);
    }

    public void sendEmail(String recipient, String emailBody, String emailSubject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setText(emailBody);
        simpleMailMessage.setSubject(emailSubject);
        javaMailSender.send(simpleMailMessage);
    }
}
