package com.willowtree.test.repository;

import com.willowtree.test.data.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MetricsRepository extends MongoRepository<Event, Long> {

    List<Event> findByUserId(String userId);
}
