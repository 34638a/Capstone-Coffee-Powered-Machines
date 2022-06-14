package au.com.qut.cpm.capstone.system.publication.database;

import au.com.qut.cpm.capstone.system.publication.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, UUID> {
}