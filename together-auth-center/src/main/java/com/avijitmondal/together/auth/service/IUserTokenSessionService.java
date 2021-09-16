package com.avijitmondal.together.auth.service;

import com.avijitmondal.together.auth.model.UserTokenSession;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserTokenSessionService {

    ValidMappingResponse isValidUserTokenSessionMapping(UserTokenSession userTokenSession) throws UsernameNotFoundException;

    UserTokenSession saveUserTokenSessionMapping(UserTokenSession userTokenSession);

    class ValidMappingResponse {

        private boolean valid;
        private UserTokenSession userTokenSession;
        
        public ValidMappingResponse(boolean valid, UserTokenSession userTokenSession) {
            this.valid = valid;
            this.userTokenSession = userTokenSession;
        }

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public UserTokenSession getUserTokenSession() {
            return userTokenSession;
        }

        public void setUserTokenSession(UserTokenSession userTokenSession) {
            this.userTokenSession = userTokenSession;
        }
    }

}
