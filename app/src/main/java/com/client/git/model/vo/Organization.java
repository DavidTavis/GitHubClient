package com.client.git.model.vo;

import java.io.Serializable;

/**
 * Created by TechnoA on 22.09.2017.
 */

public class Organization implements Serializable {
    private String name;
    private String location;
    private String blog;
    private String avatar;

    public Organization(String name, String location, String blog, String avatar) {
        this.name = name;
        this.location = location;
        this.blog = blog;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", blog='" + blog + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
