package com.woorinaru.rest.security.token.identity;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

@Component
public class GoogleIdTokenVerifier implements IdTokenVerifier {

    @Value("${google.clientid}")
    private String clientId;

    private static final JacksonFactory jacksonFactory = new JacksonFactory();
    private com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier verifier;

    public GoogleIdTokenVerifier() throws GeneralSecurityException, IOException {
        HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
        JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        this.verifier = new com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
            .setAudience(Collections.singletonList(clientId))
            .build();
    }

    public GoogleIdTokenVerifier(com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier verifier) throws GeneralSecurityException, IOException {
        this.verifier = verifier;
    }

    @Override
    public boolean canVerify(String token) {
        try {
            return Objects.nonNull(this.verifier.verify(token));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Map<String, Object>> getClaims(String token) {

        try {
            GoogleIdToken idToken = this.verifier.verify(token);

            if (Objects.nonNull(idToken)) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
                String locale = (String) payload.get("locale");
                String familyName = (String) payload.get("family_name");
                String givenName = (String) payload.get("given_name");

                Map<String, Object> claims = new HashMap<>();
                claims.put(IdTokenClaimKeys.EMAIL, email);
                claims.put(IdTokenClaimKeys.NAME, name);
                claims.put(IdTokenClaimKeys.PICTURE, pictureUrl);
                claims.put(IdTokenClaimKeys.FAMILY_NAME, familyName);
                claims.put(IdTokenClaimKeys.GIVEN_NAME, givenName);

                return Optional.of(claims);
            }

        } catch (Exception e) {
            return Optional.empty();
        }

        return Optional.empty();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
