package com.avijitmondal.together.auth.service;

import com.avijitmondal.together.auth.model.UserTokenSession;
import com.avijitmondal.together.auth.repository.UserTokenSessionRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Service
public class UserTokenSessionService implements IUserTokenSessionService {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserTokenSessionRepository userTokenSessionRepository;

    @Override
    public ValidMappingResponse isValidUserTokenSessionMapping(UserTokenSession userTokenSession) throws UsernameNotFoundException {

        String username = userTokenSession.getUsername();
        UserTokenSession userTokenSessionFromDB = userTokenSessionRepository.findOneByUsername(username);

        if (Objects.isNull(userTokenSessionFromDB)) {

            logger.error("User " + username + " mapping with token is not found in the database.");
            throw new UsernameNotFoundException("User " + username + "  mapping with token is not found in the database.");
        }

        LocalDateTime currentSystemTime = LocalDateTime.now();
        ZonedDateTime currentZonedDateTime = currentSystemTime.atZone(ZoneId.systemDefault());
        long currentTimeInMillis = currentZonedDateTime.toInstant().toEpochMilli();

        ZonedDateTime dataBaseZonedDateTime = userTokenSessionFromDB.getCreatedTime().atZone(ZoneId.systemDefault());

        // tokenTimeInMillis = created_time in millis + expiry time (seconds) * 1000.
        long  tokenTimeInMillis = dataBaseZonedDateTime.toInstant().toEpochMilli() + (userTokenSessionFromDB.getExpiryTime() * 1000);

        if ( currentTimeInMillis >= tokenTimeInMillis) {

            logger.info("User " + username + " token has expired. Please generate new token. Deleting the expired token mapping.");
            userTokenSessionRepository.delete(userTokenSessionFromDB);
            throw new UsernameNotFoundException("User " + username + " token has expired. Please generate new token.");

        }else if(!userTokenSession.equals(userTokenSessionFromDB)) {

            if (!userTokenSessionFromDB.getToken().equals(userTokenSession.getToken())){
                logger.info("User "+userTokenSession.getUsername()+ " has invalid user and token mapping. Please generate new token.");

            } else {
                logger.info("User "+userTokenSession.getUsername()+ " has invalid user and session-id mapping. Please generate new token.");
            }

            logger.info("So, Deleting the invalid mapping.");
            userTokenSessionRepository.delete(userTokenSessionFromDB);
            throw new UsernameNotFoundException("User " + username + " has invalid user, session-id and token mapping. Please generate new token.");

        }else {

            logger.info("User " + username + " has valid token.");
            return new ValidMappingResponse(true, userTokenSessionFromDB);
        }

    }

    @Override
    public UserTokenSession saveUserTokenSessionMapping(UserTokenSession userTokenSession) {

        UserTokenSession userTokenSessionFromDB = userTokenSessionRepository.findOneByUsername(userTokenSession.getUsername());

        if (Objects.nonNull(userTokenSessionFromDB)) {

            if (userTokenSessionFromDB.equals(userTokenSession)) {
                logger.info("User "+userTokenSession.getUsername()+ " making login call again with same token and session-id.");

            } else if (!userTokenSessionFromDB.getToken().equals(userTokenSession.getToken())){
                logger.info("User "+userTokenSession.getUsername()+ " making login call with new token");

            } else {
                logger.info("User "+userTokenSession.getUsername()+ " making login call with different session-id");

            }
            logger.info("So, Deleting older mapping from tbl_user_token_session."+userTokenSessionFromDB);
            userTokenSessionRepository.delete(userTokenSessionFromDB);

        }

        return userTokenSessionRepository.save(userTokenSession);
    }
}
