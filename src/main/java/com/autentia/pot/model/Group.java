package com.autentia.pot.model;

import javax.persistence.*;
import java.util.List;

@Entity(name="pot")
public class Group {
    private Long id;
    private String name;
    private List<Friend> friends;

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
}
