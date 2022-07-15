package com.autentia.pot.service;

import com.autentia.pot.model.Group;
import com.autentia.pot.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    @Autowired
    private GroupRepository repository;

    public void addGroup(Group group){
        repository.save(group);
    }

}
