package com.willowtree.test.repository;

import com.willowtree.test.data.Event;
import com.willowtree.test.data.NameChallenge;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NameChallengeRepository extends MongoRepository<NameChallenge, Long> {

    NameChallenge findByQuestionId(String questionId);
}
