package com.willowtree.test.data;

import java.util.List;
import java.util.UUID;

public class ImageChallenge {

    private final ImageRecord image;
    private final List<NameRecord> names;
    private final String questionId;

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
}

