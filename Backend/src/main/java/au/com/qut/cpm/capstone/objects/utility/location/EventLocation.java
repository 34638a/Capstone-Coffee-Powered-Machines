package au.com.qut.cpm.capstone.objects.utility.location;

import lombok.Data;

@Data
public class EventLocation {

    public enum LocationType {
        ONLINE,
        IN_PERSON,
        BOTH
    }

    private LocationType locationType;

    public String getLocationType() {
        String loc = "";
        switch (locationType) {
            case ONLINE: loc += "Online Only."; break;
            case IN_PERSON: loc += "PLACEHOLDER TEXT"; break;
            case BOTH: loc += "Online or In Person at "; break;
        }
        return loc;
    }
}
