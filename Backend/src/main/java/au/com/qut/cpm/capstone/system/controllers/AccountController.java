package au.com.qut.cpm.capstone.system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {


    @RequestMapping("/login")
    public String login() {
        return "accounts/login";
    }

    @RequestMapping("/reset")
    public String reset() {
        return "accounts/reset-password";
    }

    @RequestMapping("/view")
    public String view() {
        return "accounts/user-profile";
    }
}
