package com.avijit.together.server.model.bean;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.avijit.together.server.model.Authority;
import com.avijit.together.server.model.Credential;

/**
 * @author avijit
 */
public final class CredentialBeanFactory {

    private CredentialBeanFactory() {
    }

    /**
     * @param credential
     * @return
     */
    public static CredentialBean create(Credential credential) {
        return new CredentialBean(credential.getId(), credential.getUsername(), credential.getEmail(),
                credential.getPassword(), mapToGrantedAuthorities(credential.getAuthorities()), credential.isEnabled(),
                credential.getLastPasswordResetDate());
    }

    /**
     * @param authorities
     * @return
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthorityRole().name()))
                .collect(Collectors.toList());
    }
}
