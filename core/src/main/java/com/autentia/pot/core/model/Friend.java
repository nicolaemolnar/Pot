package com.autentia.pot.core.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Friend {

    private long id;
    private String name;

    public Friend(String name){
        this.name = name;
    }

    public Friend(long id, String name){
        this.id = id;
        this.name = name;
    }

    public Friend() {
    }

    // GETTERS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    // SETTERS
    public void setId(long  id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friend friend = (Friend) o;
        return id == friend.id && Objects.equals(name, friend.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
