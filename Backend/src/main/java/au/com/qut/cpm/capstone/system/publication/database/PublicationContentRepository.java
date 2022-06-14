package au.com.qut.cpm.capstone.system.publication.database;

import au.com.qut.cpm.capstone.system.publication.PublicationContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublicationContentRepository extends JpaRepository<PublicationContent, UUID> {
}