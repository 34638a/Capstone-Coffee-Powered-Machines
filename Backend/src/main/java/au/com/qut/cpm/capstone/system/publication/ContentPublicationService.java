package au.com.qut.cpm.capstone.system.publication;

import au.com.qut.cpm.capstone.system.publication.database.PublicationContentRepository;
import au.com.qut.cpm.capstone.system.publication.database.PublicationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ContentPublicationService {

    public static String contentStoragePath;
    public static PublicationRepository publicationRepository;
    public static PublicationContentRepository publicationContentRepository;

    public ContentPublicationService(@Value("${content.storage.path}") String contentStoragePath, PublicationRepository publicationRepository, PublicationContentRepository publicationContentRepository) {
        ContentPublicationService.contentStoragePath = contentStoragePath;
        ContentPublicationService.publicationRepository = publicationRepository;
        ContentPublicationService.publicationContentRepository = publicationContentRepository;
    }


    public static Publication getPublication(UUID uuid) {
        return publicationRepository.getById(uuid);
    }

    public static Publication savePublication(Publication publication) {
        return publicationRepository.saveAndFlush(publication);
    }

    public static PublicationContent getPublicationContent(UUID uuid) {
        return publicationContentRepository.getById(uuid);
    }

    public static PublicationContent savePublicationContent(PublicationContent publicationContent) {
        return publicationContentRepository.saveAndFlush(publicationContent);
    }
}
