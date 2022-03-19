package au.com.qut.cpm.capstone.security.repository;

import au.com.qut.cpm.capstone.security.object.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserDetails> findByUsername(String username);
}
