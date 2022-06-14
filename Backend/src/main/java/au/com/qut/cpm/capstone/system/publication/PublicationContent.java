package au.com.qut.cpm.capstone.system.publication;

import au.com.qut.cpm.capstone.utility.resource.JsonResource;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.core.io.FileSystemResource;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.IOException;

@Entity
@Getter
@Setter
@ToString
public final class PublicationContent extends PublicationMeta {

    @ManyToOne(optional = false)
    @JoinColumn(name = "publication_id", nullable = false)
    @ToString.Exclude
    private Publication publication;
    private PublishedContentStatus status;

    public FileSystemResource getPublicationResource() throws IOException {
        if (this.getId() == null) throw new IOException("No ID available to reference file with");
        return new FileSystemResource(
                ContentPublicationService.contentStoragePath + "/" + this.getId() + ".json"
        );
    }

    /**
     * Read this content file to a POJO.
     * @param castTo POJO to cast to.
     * @return POJO.
     * @param <T> Generic class type for casting.
     * @throws IOException
     */
    public <T extends PublicationContentReference> T getJsonResourceFileAsObject(Class<T> castTo) throws IOException {
        T obj = JsonResource.mapper.readValue(
                getPublicationResource().getInputStream(),
                castTo);
        obj.setPublication(this.getPublication());
        return obj;
    }

    public PublicationContent writeJsonResourceFileFromObject(PublicationContentReference object) throws IOException {
        JsonResource.mapper.writerWithDefaultPrettyPrinter().writeValue(
                getPublicationResource().getFile(),
                object
        );
        return this;
    }
}
