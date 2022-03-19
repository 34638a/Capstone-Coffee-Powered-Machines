package au.com.qut.cpm.capstone.security.object.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityRoles implements GrantedAuthority {

    public static final String SUPER_ADMIN = "SUPER_ADMIN";
    /**
     * Permission to create events.
     */
    public static final String EVENT_AUTHOR = "EVENT_AUTHOR";
    public static final String BOOK_ADMIN = "BOOK_ADMIN";

    private String authority;
}
