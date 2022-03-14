package au.com.qut.cpm.capstone.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountModel {

    private String username;
    private String email;
    private String password;
}
