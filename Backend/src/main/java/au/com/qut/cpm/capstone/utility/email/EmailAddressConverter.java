package au.com.qut.cpm.capstone.utility.email;

import org.apache.commons.validator.ValidatorException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EmailAddressConverter implements AttributeConverter<EmailAddress, String> {

    @Override
    public String convertToDatabaseColumn(EmailAddress emailAddress) {
        return emailAddress.getAddressString();
    }

    @Override
    public EmailAddress convertToEntityAttribute(String string) {
        EmailAddress emailAddress = new EmailAddress();
        if (!emailAddress.setAddressString(string)) throw new ConversionFailedException(
                TypeDescriptor.valueOf(String.class),
                TypeDescriptor.valueOf(EmailAddress.class),
                string, new ValidatorException("Unable to parse provided email address as a valid email.")
        );
        return emailAddress;
    }
}
