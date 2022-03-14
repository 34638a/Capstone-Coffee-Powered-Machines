package au.com.qut.cpm.capstone.security.repository;

import au.com.qut.cpm.capstone.security.entity.UserAccountEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserAccountEventRepository extends JpaRepository<UserAccountEvent, UUID> {
}
