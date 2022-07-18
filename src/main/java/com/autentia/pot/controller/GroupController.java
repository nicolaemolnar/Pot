package com.autentia.pot.controller;

import com.autentia.pot.model.DTO.GroupDTO;
import com.autentia.pot.model.Friend;
import com.autentia.pot.model.Group;
import com.autentia.pot.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GroupController {

    @Autowired
    private GroupService service;

    @GetMapping("/groups")
    public List<Group> getAllGroups(){
        return service.getAllGroups();
    }

    @PostMapping("/v2/groups")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGroup(@RequestBody Group group){
        service.addGroup(group);
    }

    // TODO: Decide if this functionality is needed
    /*@GetMapping("/groups/users")
    public List<Friend> getUsersOf(@RequestBody Group group){

    }*/

    @PostMapping("/v2/groups/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUserToGroup(@RequestBody GroupDTO dto){
        service.addFriendToGroup(dto.getFriend(), dto.getGroup());
    }

    @GetMapping("/groups/{groupId}")
    public Group getGroupBy(@PathVariable("groupId") Long groupId){
        return service.getGroupBy(groupId);
    }
}
