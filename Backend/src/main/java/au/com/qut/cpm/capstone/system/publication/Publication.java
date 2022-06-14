package au.com.qut.cpm.capstone.system.publication;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.IOException;

@Entity
@Getter
@Setter
@ToString
public final class Publication extends PublicationMeta {

    private PublicationContentType publicationContentType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "content")
    private PublicationContent publicationContent;

    public void saveNewContentFile(PublicationContentReference object) throws IOException {
        PublicationContent content = new PublicationContent()
                .setPublication(this)
                .setStatus(PublishedContentStatus.LIVE)
                ;
        if (object.getPublication() == null) object.setPublication(this);
        ContentPublicationService.savePublicationContent(content).writeJsonResourceFileFromObject(object);
        this.publicationContent = content;
        ContentPublicationService.savePublication(this);
    }
}
