package com.ilyamur.espresso.data.repository;

import com.ilyamur.espresso.data.entity.EchoMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EchoMessageHistoryRepository extends MongoRepository<EchoMessage, String> {
}
