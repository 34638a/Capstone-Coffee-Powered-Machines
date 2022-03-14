package au.com.qut.cpm.capstone.controllers.dynamic.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserProfileController {


    @GetMapping("/user/{username}")
    public String openUserProfile(Model model, @PathVariable String username) {

        boolean canAdminister = false;
        return canAdminister ? "/dynamic/user/user-administrate" : "/dynamic/user/user-view";
    }
}
