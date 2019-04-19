package com.willowtree.test.data;

public class ChallengeResponse {

    private NameRecord name;
    private ImageRecord image;
    private String questionId;
    private String user;

    public NameRecord getName() {
        return name;
    }

    public void setName(NameRecord name) {
        this.name = name;
    }

    public ImageRecord getImage() {
        return image;
    }

    public void setImage(ImageRecord image) {
        this.image = image;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
