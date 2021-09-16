package com.avijitmondal.together.auth.repository;

import com.avijitmondal.together.auth.model.UserTokenSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("userTokenSessionRepository")
public interface UserTokenSessionRepository extends CrudRepository<UserTokenSession, UUID> {

    Optional<UserTokenSession> findOneByUsername(String username);
}
