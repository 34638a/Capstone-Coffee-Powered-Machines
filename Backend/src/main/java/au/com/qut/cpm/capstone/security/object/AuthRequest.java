package au.com.qut.cpm.capstone.security.object;

import au.com.qut.cpm.capstone.security.account.service.UserAccountDetailService;
import au.com.qut.cpm.capstone.security.jwt.filter.JwtTokenUtil;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;

@Data
public class AuthRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;
    private String username;
    private String password;


    public String authenticate(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserAccountDetailService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);

        return jwtTokenUtil.generateToken(userDetails);
    }
}
