package au.com.qut.cpm.capstone.security.repository;

import au.com.qut.cpm.capstone.security.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

    List<UserAccount> findUserAccountsByPrivateEmail(String email);
}
