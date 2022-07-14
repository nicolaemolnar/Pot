package com.autentia.pot.model;

import javax.persistence.*;
import java.util.List;

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
}
