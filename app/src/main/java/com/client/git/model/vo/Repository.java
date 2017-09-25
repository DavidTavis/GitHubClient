package com.client.git.model.vo;

import java.io.Serializable;

/**
 * Created by TechnoA on 22.09.2017.
 */

public class Repository implements Serializable{
    private String name;
    private String description;

    public Repository(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
