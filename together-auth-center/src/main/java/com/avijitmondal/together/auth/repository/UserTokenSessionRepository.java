package com.avijitmondal.together.auth.repository;

import com.avijitmondal.together.auth.model.UserTokenSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenSessionRepository extends CrudRepository<UserTokenSession, Long> {

    UserTokenSession findOneByUsername(String username);
}
