package au.com.qut.cpm.capstone.security.account.service;

import au.com.qut.cpm.capstone.security.account.object.UserAccountDetails;
import au.com.qut.cpm.capstone.security.account.object.database.UserAccount;
import au.com.qut.cpm.capstone.security.account.object.database.UserAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAccountDetailService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountDetailService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = userAccountRepository.findUserAccountByEmail(username);
        if (user != null) return new UserAccountDetails(user);
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
