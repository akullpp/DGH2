package de.akull.dgh.models;

import com.fasterxml.jackson.annotation.JsonProperty;


// Domain entity
public class Name {
    @JsonProperty
    public int id;

    @JsonProperty
    public String name;

    public Name(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
