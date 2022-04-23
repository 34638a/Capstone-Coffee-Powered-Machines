package au.com.qut.cpm.capstone.security.account.object.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

    UserAccount findUserAccountByEmail(String email);

    UserAccount findUserAccountByContextKey(String contextKey);
}
