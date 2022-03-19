package au.com.qut.cpm.capstone.system.listings.data.dto;

import au.com.qut.cpm.capstone.system.socials.socialmedia.SocialMedia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListedEventEntityDto implements Serializable {
    private String listingTitle;
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String description;
    private List<SocialMedia> socialMedia;
    private UUID listedEventHostId;
}
