package com.autentia.pot.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name="pot")
public class Pot {
    private Long id;
    private String name;
    private List<Friend> friends;

    public Pot(){
        friends = new ArrayList<>();
    }

    public Pot(Long id){
        this.id = id;
        friends = new ArrayList<>();
    }

    public Pot(String name, List<Friend> friends){
        this.name = name;
        this.friends = friends;
    }

    // SETTERS
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public void addFriend(Friend friend) {
        this.friends.add(friend);
    }

    // GETTERS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @ManyToMany
    public List<Friend> getFriends() {
        return friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pot group = (Pot) o;
        return Objects.equals(id, group.id); //&& Objects.equals(name, group.name) && Objects.equals(friends, group.friends);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, friends);
    }

    @Override
    public String toString() {
        return "Pot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", friends=" + friends +
                '}';
    }
}
