package com.autentia.pot.service;

import com.autentia.pot.model.Friend;
import com.autentia.pot.model.Pot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PotService {
    private com.autentia.pot.repository.PotRepository repository;

    public PotService(com.autentia.pot.repository.PotRepository repository){
        this.repository = repository;
    }

    public List<Pot> getAllPots(){
        return repository.findAll();
    }

    public void addPot(Pot pot){
        repository.save(pot);
    }

    public void addFriendToPot(Friend friend, Pot pot){
        pot.addFriend(friend);
        repository.save(pot);
    }

    public Pot getPotById(Long potId){
        return repository.findPotById(potId);
    }

    public List<Friend> getFriendsOf(Long potId) {
        return repository.findPotById(potId).getFriends();
    }
}
