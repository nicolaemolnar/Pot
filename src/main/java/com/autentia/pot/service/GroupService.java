package com.autentia.pot.service;

import com.autentia.pot.model.Friend;
import com.autentia.pot.model.Group;
import com.autentia.pot.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository repository;

    public List<Group> getAllGroups(){
        return repository.findAll();
    }

    public void addGroup(Group group){
        repository.save(group);
    }

    public void addFriendToGroup(Friend friend, Group group){
        group.addFriend(friend);
        repository.save(group);
    }

}
