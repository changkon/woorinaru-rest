package com.woorinaru.rest.dto.management.administration;

public class Resource {
    private int id;
    private byte[] resource;
    private String description;

    public Resource() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getResource() {
        return resource;
    }

    public String getDescription() {
        return description;
    }

    public void setResource(byte[] resource) {
        this.resource = resource;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
