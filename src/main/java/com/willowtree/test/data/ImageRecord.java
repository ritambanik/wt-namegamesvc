package com.willowtree.test.data;

public class ImageRecord {

    private final String id;
    private final Headshot headshot;

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

}
