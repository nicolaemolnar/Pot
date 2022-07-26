package com.autentia.pot.model.dto;

import com.autentia.pot.model.Friend;
import com.autentia.pot.model.Pot;

public class GroupDTO {
    private final Friend friend;
    private final Pot group;

    public GroupDTO(Friend friend, Pot group){
        this.friend = friend;
        this.group = group;
    }

    public Friend getFriend(){
        return this.friend;
    }

    public Pot getGroup(){
        return this.group;
    }
}
