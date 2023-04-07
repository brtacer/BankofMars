package com.berat.repository;

import com.berat.model.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository extends MongoRepository<Transaction,String> {

    Slice<Transaction> findByAccountId(Long accountId, Pageable pageable);
}
