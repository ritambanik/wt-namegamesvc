package com.willowtree.test.data;

public class ImageRecord {

    private String id;
    private Headshot headshot;

    public ImageRecord() {
    }

    public ImageRecord(String id, Headshot headshot) {
        this.id = id;
        this.headshot = headshot;
    }

    public String getId() {
        return id;
    }

    public Headshot getHeadshot() {
        return headshot;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHeadshot(Headshot headshot) {
        this.headshot = headshot;
    }
}
