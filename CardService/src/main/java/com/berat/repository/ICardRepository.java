package com.berat.repository;

import com.berat.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICardRepository extends MongoRepository<Card,String> {
    List<Card> findAllByAccountId(Long id);
}
