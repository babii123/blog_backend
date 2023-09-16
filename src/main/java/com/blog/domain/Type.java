package com.blog.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Type {
    private String id;
    @JsonProperty("label")
    private String typeName;
    private String key;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id='" + id + '\'' +
                ", typeName='" + typeName + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
