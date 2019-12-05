package com.woorinaru.rest.security.token.identity;

import java.util.Map;
import java.util.Optional;

/**
 * Validates Id tokens retrieved from SSO against an Identity Provider.
 * https://openid.net/specs/openid-connect-core-1_0.html
 */
public interface IdTokenVerifier {

    /**
     * Given an access token, determine whether this token can be verified against this verifier
     * @param token
     * @return
     */
    boolean canVerify(String token);

    /**
     * @return list of claims specified in OpenId
     * @see IdTokenClaimKeys for list of keys that can be retrieved
     */
    Optional<Map<String, Object>> getClaims(String token);

    default boolean verify(String token) {
        Optional<Map<String, Object>> claims = getClaims(token);
        boolean isValid = claims.isPresent();
        return isValid;
    }

}
