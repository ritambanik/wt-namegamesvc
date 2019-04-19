package com.willowtree.test.data;

public class Profile {


    private String id;
    private String type;
    private String slug;
    private String jobTitle;
    private String firstName;
    private String lastName;
    private Headshot headshot;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Headshot getHeadshot() {
        return headshot;
    }

    public void setHeadshot(Headshot headshot) {
        this.headshot = headshot;
    }
}
