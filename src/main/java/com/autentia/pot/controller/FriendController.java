package com.autentia.pot.controller;

import com.autentia.pot.model.Friend;
import com.autentia.pot.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FriendController {

    @Autowired
    private FriendService service;

    @GetMapping("/friends")
    public List<Friend> getUsers(){
        return service.getAllFriends();
    }

    @GetMapping(value = "/friends/{id}")
    public Friend getUser(@PathVariable(value = "id") Long id){
        return service.getFriendById(id);
    }

    @PostMapping("/v2/friends")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody Friend friend){
        service.addFriend(friend);
    }
}
