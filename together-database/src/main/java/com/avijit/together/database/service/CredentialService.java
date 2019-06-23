/*****************************************************************************
 * FILE NAME   : CredentialService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 24, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.database.service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.avijit.together.core.model.Authority;
import com.avijit.together.core.model.AuthorityRole;
import com.avijit.together.core.model.Credential;
import com.avijit.together.database.repository.ICredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.avijit.together.core.exception.ErrorCode;
import com.avijit.together.core.exception.IErrorDetails;
import com.avijit.together.core.exception.TogetherException;
import com.avijit.together.core.model.bean.CredentialBeanFactory;

/**
 * @author avijit
 */
@Service("credentialService")
public class CredentialService implements IAuthenticationService, UserDetailsService {

    /**
     *
     */
    @Autowired
    private ICredentialRepository iCredentialRepository;

    @Autowired
    private AuthorityService authorityService;

    /*
     * (non-Javadoc)
     *
     * @see com.avijit.together.database.service.IAuthenticationService#findAll(org.
     * springframework.data.domain.Pageable)
     */
    @Override
    public Page<Credential> findAll(Pageable pageable) {
        return iCredentialRepository.findAll(pageable);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.avijit.together.database.service.IAuthenticationService#findById(java.
     * lang.String)
     */
    @Override
    public Optional<Credential> findById(String authenticationId) throws TogetherException {
        try {
            return iCredentialRepository.findById(UUID.fromString(authenticationId));
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new TogetherException(ErrorCode.INVALID_AUTHENTICATION_ID, IErrorDetails.INVALID_AUTHENTICATION_ID);
        } catch (Exception exception) {
            throw new TogetherException(ErrorCode.INVALID_AUTHENTICATION_ID,
                    String.format(IErrorDetails.INVALID_AUTHENTICATION_ID, authenticationId));
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.avijit.together.database.service.IAuthenticationService#save(com.avijit
     * .together.server.model.Credential)
     */
    @Override
    public Credential save(Credential credential) throws TogetherException {
        try {
            credential.setId(UUID.randomUUID());
            credential.setLastPasswordResetDate(new Date());
            credential.setEnabled(true);
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(credential.getPassword());
            credential.setPassword(hashedPassword);
            credential.getAuthorities().forEach(authority -> {
                Authority tempAuthority = null;
                if (authority.getAuthorityRole() == AuthorityRole.ROLE_USER) {
                    tempAuthority = authorityService.findByAuthorityRoleUSER();
                } else if (authority.getAuthorityRole() == AuthorityRole.ROLE_ADMIN) {
                    tempAuthority = authorityService.findByAuthorityRoleADMIN();
                }
                authority.setId(tempAuthority.getId());
            });
            return iCredentialRepository.save(credential);
        } catch (Exception exception) {
            throw new TogetherException(ErrorCode.AUTHENTICATION_NOT_ADDED, IErrorDetails.UNABLE_TO_ADD_AUTHENTICATION);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.avijit.together.database.service.IAuthenticationService#delete(java.
     * lang.String)
     */
    @Override
    public boolean delete(String authenticationId) throws TogetherException {
        try {
            if (isExists(authenticationId)) {
                iCredentialRepository.deleteById(UUID.fromString(authenticationId));
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            throw new TogetherException(ErrorCode.INVALID_AUTHENTICATION_ID, IErrorDetails.INVALID_AUTHENTICATION_ID);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.core.userdetails.UserDetailsService#
     * loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            Credential credential = iCredentialRepository.findByUsername(username);
            if (credential == null) {
                throw new UsernameNotFoundException(String.format(IErrorDetails.USER_USERNAME_NOT_FOUND, username));
            } else {
                return CredentialBeanFactory.create(credential);
            }
        } catch (Exception exception) {
            throw new UsernameNotFoundException(String.format(IErrorDetails.USER_USERNAME_NOT_FOUND, username));
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.avijit.together.database.service.IAuthenticationService#isExists(java.lang.
     * String)
     */
    @Override
    public boolean isExists(String authenticationId) throws TogetherException {
        try {
            return iCredentialRepository.existsById(UUID.fromString(authenticationId));
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new TogetherException(ErrorCode.INVALID_AUTHENTICATION_ID,
                    String.format(IErrorDetails.INVALID_AUTHENTICATION_ID, authenticationId));
        }
    }

}
