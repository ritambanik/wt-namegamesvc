package com.willowtree.test.repository;

import com.willowtree.test.data.ImageChallenge;
import com.willowtree.test.data.NameChallenge;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageChallengeRepository extends MongoRepository<ImageChallenge, Long> {

    ImageChallenge findByQuestionId(String questionId);
}
