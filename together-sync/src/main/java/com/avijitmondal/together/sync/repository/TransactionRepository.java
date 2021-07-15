package com.avijitmondal.together.sync.repository;

import com.avijitmondal.together.sync.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    Page<Transaction> findBySuccessIsTrueAndCreatedLessThanEqualAndUserIdOrderByCreatedDesc(long created, String userId, Pageable pageRequest);
}
