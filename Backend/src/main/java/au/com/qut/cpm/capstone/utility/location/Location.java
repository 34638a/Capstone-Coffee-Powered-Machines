package au.com.qut.cpm.capstone.utility.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    private static final String ADDRESS_LAYOUT = "%s\n%s\n%s, %s %s\n%s";

    private String url;
    private String addressLn1, addressLn2, city, state, postalCode, country;

    @JsonIgnore
    public String getAddress() {
        return String.format(ADDRESS_LAYOUT, addressLn1, addressLn2, city, state, postalCode, country);
    }


    @JsonIgnore
    public boolean isOnline() {return url != null && url.trim().length()>0;}

    @JsonIgnore
    public boolean isInPerson() {
        return country != null && country.trim().length()>0;
    }

    @JsonIgnore
    public boolean isOnlineAndInPerson() {
        return isOnline() && isInPerson();
    }

    @JsonIgnore
    public String getLocationMessage() {
        String loc = "";
        boolean online = isOnline();
        if (online) {
            loc += "Online at " + getUrl();
        }
        if (isInPerson()) {
            if (online) {
                loc += " or ";
            }
            loc += "In Person at " + getAddress();
        }
        return loc;
    }
}
