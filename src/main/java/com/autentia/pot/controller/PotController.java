package com.autentia.pot.controller;

import com.autentia.pot.model.Friend;
import com.autentia.pot.model.dto.GroupDTO;
import com.autentia.pot.model.Pot;
import com.autentia.pot.service.PotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PotController {

    @Autowired
    private PotService service;

    @GetMapping("/pots")
    public List<Pot> getAllGroups(){
        return service.getAllPots();
    }

    @PostMapping("/v2/pots")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGroup(@RequestBody Pot group){
        service.addPot(group);
    }

    // TODO: Decide if this functionality is needed
    @GetMapping("/pots/{potId}/friends")
    public List<Friend> getFriendsOf(@PathVariable(value = "potId") Long potId){
        return service.getFriendsOf(potId);
    }

    @PostMapping("/v2/pots/friends")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUserToPot(@RequestBody GroupDTO dto){
        service.addFriendToPot(dto.getFriend(), dto.getGroup());
    }

    @GetMapping("/pots/{potId}")
    public Pot getPotById(@PathVariable("potId") Long potId){
        return service.getPotById(potId);
    }
}
