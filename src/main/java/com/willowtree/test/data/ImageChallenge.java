package com.willowtree.test.data;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document("EventChallenge")
public class ImageChallenge {

    private ImageRecord image;
    private List<NameRecord> names;
    private String questionId;

    public ImageChallenge() { }

    public ImageChallenge(ImageRecord image, List<NameRecord> names) {
        this.image = image;
        this.names = names;
        this.questionId = UUID.randomUUID().toString();;
    }

    public ImageRecord getImage() {
        return image;
    }

    public List<NameRecord> getNames() {
        return names;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setImage(ImageRecord image) {
        this.image = image;
    }

    public void setNames(List<NameRecord> names) {
        this.names = names;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}

