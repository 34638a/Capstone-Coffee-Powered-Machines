package au.com.qut.cpm.capstone.system.publication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.io.IOException;

@Data
public abstract class PublicationContentReference {

    @JsonIgnore
    @ToString.Exclude
    private Publication publication;

    /**
     * Save this content to the publication.
     * @throws IOException
     */
    @JsonIgnore
    public final void save() throws IOException {
        publication.saveNewContentFile(this);
    }
}
