package com.willowtree.test.data;

public class NameRecord {

    private final String firstName;
    private final String lastName;
    private final String id;

    public NameRecord(String firstName, String lastName, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

}
