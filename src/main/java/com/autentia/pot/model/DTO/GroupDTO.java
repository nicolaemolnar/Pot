package com.autentia.pot.model.DTO;

import com.autentia.pot.model.Friend;
import com.autentia.pot.model.Group;

public class GroupDTO {
    private Friend friend;
    private Group group;

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
