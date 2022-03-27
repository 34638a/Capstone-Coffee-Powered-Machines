package au.com.qut.cpm.capstone.system.administration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "dynamic/account/login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "dynamic/account/register";
    }

    @GetMapping("/reset")
    public String resetPage() {
        return "dynamic/account/reset";
    }
}
