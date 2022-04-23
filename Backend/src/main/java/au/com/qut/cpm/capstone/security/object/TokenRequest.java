package au.com.qut.cpm.capstone.security.object;

import au.com.qut.cpm.capstone.security.jwt.filter.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Collections;

@Data
@AllArgsConstructor
public final class TokenRequest implements Serializable{

    private static final long serialVersionUID = -8091879091924046844L;
    private String token;

    public ResponseEntity<?> setTokenInCookie(HttpStatus status) {
        return setTokenInCookie(new ResponseEntity<>(this, status));
    }

    public ResponseEntity<?> setTokenInCookie(ResponseEntity<?> response) {

        //Create the cookie
        ResponseCookie responseCookie = ResponseCookie
                .from("authorisation",token)
                .maxAge(JwtTokenUtil.JWT_TOKEN_VALIDITY)
                .secure(true)
                .httpOnly(true)
                .path("/")
                .build();

        //Add cookie to response
        HttpHeaders newHeaders = new HttpHeaders();
        newHeaders.addAll(response.getHeaders());
        newHeaders.put(HttpHeaders.SET_COOKIE, Collections.singletonList(responseCookie.toString()));
        return new ResponseEntity<>(response.getBody(), newHeaders, response.getStatusCode());
    }
}
