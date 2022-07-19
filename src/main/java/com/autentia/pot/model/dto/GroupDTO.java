package com.autentia.pot.model.dto;

import com.autentia.pot.model.Friend;
import com.autentia.pot.model.Group;

public class GroupDTO {
    private final Friend friend;
    private final Group group;

    public GroupDTO(Friend friend, Group group){
        this.friend = friend;
        this.group = group;
    }

    public Friend getFriend(){
        return this.friend;
    }

    public Group getGroup(){
        return this.group;
    }
}
