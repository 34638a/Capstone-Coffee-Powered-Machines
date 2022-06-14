package au.com.qut.cpm.capstone.utility.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonResource {
    public static final ObjectMapper mapper = new ObjectMapper();
}
