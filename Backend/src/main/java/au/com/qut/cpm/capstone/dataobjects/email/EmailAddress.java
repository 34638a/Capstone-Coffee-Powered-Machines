package au.com.qut.cpm.capstone.dataobjects.email;

import lombok.Getter;
import org.apache.commons.validator.routines.EmailValidator;


public class EmailAddress {

    private static final EmailValidator emailValidator = EmailValidator.getInstance();

    @Getter
    private String addressString;

    public boolean setAddressString(String addressString) {
        if (emailValidator.isValid(addressString)) {
            this.addressString = addressString;
            return true;
        }
        return false;
    }
}
