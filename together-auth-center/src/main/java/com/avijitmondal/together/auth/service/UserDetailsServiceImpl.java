package com.avijitmondal.together.auth.service;

import com.avijitmondal.together.auth.model.User;
import com.avijitmondal.together.auth.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<User> users = userRepository.findAll();
        logger.debug("Available users are " + users);

        User userFromDataBase = userRepository.findOneByUsername(username);
        if (userFromDataBase == null) {
            logger.info("User " + username + " was not found in the database");
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        return userFromDataBase;

    }
}
