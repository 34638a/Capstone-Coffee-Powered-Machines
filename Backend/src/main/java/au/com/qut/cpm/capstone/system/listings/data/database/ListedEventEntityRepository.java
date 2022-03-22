package au.com.qut.cpm.capstone.system.listings.data.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ListedEventEntityRepository extends JpaRepository<ListedEventEntity, UUID> {
}