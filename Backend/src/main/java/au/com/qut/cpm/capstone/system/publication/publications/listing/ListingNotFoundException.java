package au.com.qut.cpm.capstone.system.publication.publications.listing;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ListingNotFoundException extends RuntimeException {
}
