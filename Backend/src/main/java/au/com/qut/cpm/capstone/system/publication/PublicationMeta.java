package au.com.qut.cpm.capstone.system.publication;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@MappedSuperclass
@ToString
public abstract class PublicationMeta {
    @Id
    @Column(name = "id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private Timestamp firstPublished;
    private Timestamp lastUpdated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PublicationMeta)) return false;
        PublicationMeta that = (PublicationMeta) o;
        return getId().equals(that.getId()) && getFirstPublished().equals(that.getFirstPublished()) && getLastUpdated().equals(that.getLastUpdated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstPublished(), getLastUpdated());
    }

    /**
     * Callback when the Entity is first saved.
     */
    @PrePersist
    public void onPublicationCreated() {
        this.setFirstPublished(Timestamp.from(Instant.now()));
        onPublicationUpdated();
    }

    /**
     * Callback when the Entity is updated.
     */
    @PreUpdate
    public void onPublicationUpdated() {
        this.setLastUpdated(Timestamp.from(Instant.now()));
    }
}
