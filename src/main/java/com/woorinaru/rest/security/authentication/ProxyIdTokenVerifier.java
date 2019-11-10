package com.woorinaru.rest.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Component
public class ProxyIdTokenVerifier implements IdTokenVerifier {

    private Collection<IdTokenVerifier> verifiers;

    public ProxyIdTokenVerifier() {}

    @Override
    public boolean canVerify(String token) {
        return this.verifiers.stream()
            .anyMatch(verifier -> verifier.canVerify(token));
    }

    @Override
    public Optional<Map<String, Object>> getClaims(String token) {
        Optional<IdTokenVerifier> verifier = this.verifiers.stream()
            .filter(v -> v.canVerify(token))
            .findFirst();

        if (verifier.isPresent()) {
            return verifier.get().getClaims(token);
        }

        return Optional.empty();
    }

    public Collection<IdTokenVerifier> getVerifiers() {
        return verifiers;
    }

    @Autowired
    public void setVerifiers(Collection<IdTokenVerifier> verifiers) {
        this.verifiers = verifiers;
    }
}
