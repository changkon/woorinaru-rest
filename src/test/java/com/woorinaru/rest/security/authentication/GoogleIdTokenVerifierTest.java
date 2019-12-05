package com.woorinaru.rest.security.authentication;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.woorinaru.rest.security.token.identity.GoogleIdTokenVerifier;
import com.woorinaru.rest.security.token.identity.IdTokenClaimKeys;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GoogleIdTokenVerifierTest {

    @Test
    public void testInvalidTokenReturnsEmptyClaim() throws GeneralSecurityException, IOException {
        // GIVEN
        com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier mockGoogleVerifier = mock(com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier.class);

        when(mockGoogleVerifier.verify(any(String.class))).thenReturn(null);
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier(mockGoogleVerifier);

        // WHEN
        Optional<Map<String, Object>> claims = verifier.getClaims("");

        // THEN
        assertThat(claims).isEmpty();
    }

    @Test
    public void testValidTokenReturnsPopulatedClaim() throws GeneralSecurityException, IOException {
        // GIVEN
        com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier mockGoogleVerifier = mock(com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier.class);
        GoogleIdToken mockGoogleIdToken = mock(GoogleIdToken.class);

        GoogleIdToken.Payload payload = new GoogleIdToken.Payload();
        payload.setEmail("random@test.com");
        payload.setEmailVerified(false);
        payload.set("name", "test name");
        payload.set("picture", "picture url");
        payload.set("locale", "en");
        payload.set("family_name", "Han");
        payload.set("given_name", "Mike");

        when(mockGoogleVerifier.verify(any(String.class))).thenReturn(mockGoogleIdToken);
        when(mockGoogleIdToken.getPayload()).thenReturn(payload);

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier(mockGoogleVerifier);

        // WHEN
        Optional<Map<String, Object>> claims = verifier.getClaims("");

        // THEN
        assertThat(claims).isNotEmpty();
        Map<String, Object> returnedClaims = claims.get();
        assertThat(returnedClaims.get(IdTokenClaimKeys.EMAIL)).isEqualTo("random@test.com");
        assertThat(returnedClaims.get(IdTokenClaimKeys.NAME)).isEqualTo("test name");
        assertThat(returnedClaims.get(IdTokenClaimKeys.PICTURE)).isEqualTo("picture url");
        assertThat(returnedClaims.get(IdTokenClaimKeys.FAMILY_NAME)).isEqualTo("Han");
        assertThat(returnedClaims.get(IdTokenClaimKeys.GIVEN_NAME)).isEqualTo("Mike");
    }

}
