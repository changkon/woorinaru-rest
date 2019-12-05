package com.woorinaru.rest.security.token.identity;

/**
 * Specifies standard claims returned from an ID token. The claims list retrieved from https://openid.net/specs/openid-connect-core-1_0.html.
 * This class returns a subset of claims
 */
public class IdTokenClaimKeys {
    public static final String SUB = "sub";
    public static final String NAME = "name";
    public static final String GIVEN_NAME = "given_name";
    public static final String FAMILY_NAME = "family_name";
    public static final String MIDDLE_NAME = "middle_name";
    public static final String NICKNAME = "nickname";
    public static final String PREFERRED_USERNAME = "preferred_username";
    public static final String PROFILE = "profile";
    public static final String PICTURE = "picture";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";
    public static final String BIRTHDATE = "birthdate";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String ADDRESS = "address";
}
