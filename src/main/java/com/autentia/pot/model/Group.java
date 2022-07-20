package com.autentia.pot.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name="pot")
public class Group {
    private Long id;
    private String name;
    private List<Friend> friends;

    public Group(){
        friends = new ArrayList<>();
    }

    public Group(Long id){
        this.id = id;
        friends = new ArrayList<>();
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
        Group group = (Group) o;
        return Objects.equals(id, group.id); //&& Objects.equals(name, group.name) && Objects.equals(friends, group.friends);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, friends);
    }
}
