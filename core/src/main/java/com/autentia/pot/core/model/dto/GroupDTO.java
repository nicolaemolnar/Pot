package com.autentia.pot.core.model.dto;

import com.autentia.pot.core.model.Pot;
import com.autentia.pot.core.model.Friend;

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
