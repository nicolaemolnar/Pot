package com.autentia.pot.controller;

import com.autentia.pot.model.Friend;
import com.autentia.pot.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FriendController {

    @Autowired
    private FriendService service;

    @GetMapping("/users")
    public List<Friend> getUsers(){
        return service.getAllUsers();
    }

    @GetMapping(value = "/users/{id}")
    public Friend getUser(@PathVariable(value = "id") Long id){
        return service.getFriendBy(id);
    }

    @PostMapping("/v2/users")
    public void addUser(@RequestBody String name){
        service.addFriend(new Friend(name));
    }

    @DeleteMapping(value = "/v2/users/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id){
        service.deleteFriendBy(id);
    }
}
