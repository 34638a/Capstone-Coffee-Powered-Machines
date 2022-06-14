package au.com.qut.cpm.capstone.security.account.controller;

import au.com.qut.cpm.capstone.system.io.email.EmailWrapper;
import au.com.qut.cpm.capstone.system.io.email.MailerService;
import au.com.qut.cpm.capstone.security.account.object.database.UserAccount;
import au.com.qut.cpm.capstone.security.account.object.database.UserAccountRepository;
import au.com.qut.cpm.capstone.security.account.service.UserAccountDetailService;
import au.com.qut.cpm.capstone.security.jwt.filter.JwtTokenUtil;
import au.com.qut.cpm.capstone.security.object.AuthRequest;
import au.com.qut.cpm.capstone.security.object.TokenRequest;
import au.com.qut.cpm.capstone.utility.SimpleJsonMessage;
import lombok.extern.java.Log;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

/**
 * This API is not for the authentication of a user.
 * It is only for the creation and management of the accounts of users.
 */
@RestController
@CrossOrigin
@RequestMapping("/api/account")
@Log
@Transactional
public class AccountAPI {

    private final SimpleJsonMessage rejectedMessage = new SimpleJsonMessage("Invalid Request, please try again.");
    private final SimpleJsonMessage registerMessage = new SimpleJsonMessage("Account registration successful, please check your email.");
    private final SimpleJsonMessage validateMessage = new SimpleJsonMessage("Account validation successful, please login using your credentials.");
    private final SimpleJsonMessage requestResetMessage = new SimpleJsonMessage("Password reset request received. Please check your email for confirmation.");
    private final SimpleJsonMessage resetMessage = new SimpleJsonMessage("Password reset successfully. Please login with your new password.");


    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserAccountDetailService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserAccountRepository userAccountRepository;
    private final MailerService mailerService;


    public AccountAPI(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserAccountDetailService userDetailsService, PasswordEncoder passwordEncoder, UserAccountRepository userAccountRepository, MailerService mailerService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.userAccountRepository = userAccountRepository;
        this.mailerService = mailerService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authoriseUserAccount(@RequestBody AuthRequest request) {
        log.info("User login attempted " + request.getUsername());
        try {
            String token = request.authenticate(authenticationManager, jwtTokenUtil, userDetailsService, passwordEncoder);
            return new TokenRequest(token).setTokenInCookie(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(rejectedMessage, HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUserAccount(@RequestBody AuthRequest request) {
        log.info("New user registration attempted " + request.getUsername());

        UserAccount userAccount;
        try {
            //Logic for if the account exists.
            UserDetails details = userDetailsService.loadUserByUsername(request.getUsername());
            if (details.isCredentialsNonExpired()) {
                return new ResponseEntity<>(registerMessage, HttpStatus.BAD_REQUEST);
            }
            userAccount = userAccountRepository.findUserAccountByEmail(request.getUsername());
        } catch (Exception e) {
            //Logic for if the account does not exist.
            userAccount = new UserAccount();
            userAccount.setEmail(request.getUsername());
            userAccount.setPassword(passwordEncoder.encode(request.getPassword()));
            userAccount.setRoles("USER");
            log.info(userAccount.toString());
        }

        //Send the email
        userAccount.setContextKey(RandomStringUtils.randomAlphanumeric(64));
        userAccountRepository.save(userAccount);
        if (sendNewUserEmail(userAccount))
            return new ResponseEntity<>(registerMessage, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(registerMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/register/{context}", method = RequestMethod.GET)
    public ResponseEntity<?> registerUserAccount(@PathVariable String context) {
        UserAccount userAccount = userAccountRepository.findUserAccountByContextKey(context);
        if (userAccount != null) {
            userAccount.setRegistered(true);
            userAccount.setContextKey(null);
        }
        return new ResponseEntity<>(validateMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ResponseEntity<?> resetUserAccountPassword(@RequestBody String email) {

        UserAccount userAccount = userAccountRepository.findUserAccountByEmail(email);
        if (userAccount != null) {
            userAccount.setContextKey(RandomStringUtils.randomAlphanumeric(64));
            if (userAccount.isRegistered()) {
                EmailWrapper resetPasswordRequestEmail = new EmailWrapper("emails/reset-password",
                        "Password Reset Requested", true);
                resetPasswordRequestEmail.getContext().setVariable("path", userAccount.getContextKey());
                resetPasswordRequestEmail.getSendTo().add(userAccount.getEmail());
                try {
                    mailerService.sendEmail(resetPasswordRequestEmail);
                } catch (MessagingException exception) {
                    log.warning(exception.toString());
                    return new ResponseEntity<>(requestResetMessage, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                if (sendNewUserEmail(userAccount)) return new ResponseEntity<>(requestResetMessage, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(requestResetMessage, HttpStatus.OK);
    }

    private boolean sendNewUserEmail(UserAccount userAccount) {
        EmailWrapper newAccountRequestEmail = new EmailWrapper("emails/account-confirm",
                "Complete your sign up for MiningEvents.info", true);
        newAccountRequestEmail.getContext().setVariable("path", userAccount.getContextKey());
        newAccountRequestEmail.getSendTo().add(userAccount.getEmail());

        log.info(userAccount.getContextKey());
        /*
        try {
            mailerService.sendEmail(newAccountRequestEmail);
        } catch (MessagingException exception) {
            log.warning(exception.toString());
            return true;
        }//*/
        return false;
    }

    @RequestMapping(value = "/reset/{key}", method = RequestMethod.POST)
    public ResponseEntity<?> resetUserAccountPassword(@RequestBody String newPassword, @PathVariable String key) {

        UserAccount userAccount = userAccountRepository.findUserAccountByContextKey(key);
        if (userAccount != null) userAccount.setPassword(passwordEncoder.encode(newPassword));
        return new ResponseEntity<>(resetMessage, HttpStatus.OK);
    }
}
