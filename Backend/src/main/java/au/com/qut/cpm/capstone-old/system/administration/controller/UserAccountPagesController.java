package au.com.qut.cpm.capstone.system.administration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserAccountPagesController {

    @GetMapping("/login")
    public String loginPage() {
        return "dynamic/account/login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "dynamic/account/register";
    }

    @GetMapping("/reset")
    public String resetRequestPage() {
        return "dynamic/account/reset";
    }

    @GetMapping("/reset/{key}")
    public String resetPage(@PathVariable String key) {
        return "dynamic/account/reset";
    }
}
