package com.willowtree.test.data;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document("NameChallenge")
public class NameChallenge {

    private NameRecord name;
    private List<ImageRecord> images;
    private String questionId;

    public NameChallenge() {
    }

    public NameChallenge(NameRecord name, List<ImageRecord> images) {
        this.name = name;
        this.images = images;
        this.questionId = UUID.randomUUID().toString();
    }

    public NameRecord getName() {
        return name;
    }

    public List<ImageRecord> getImages() {
        return images;
    }
    public String getQuestionId() {
        return questionId;
    }

}
