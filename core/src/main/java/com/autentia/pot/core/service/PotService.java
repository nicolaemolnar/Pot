package com.autentia.pot.core.service;

import com.autentia.pot.core.model.Pot;
import com.autentia.pot.core.model.Friend;
import com.autentia.pot.core.repository.PotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PotService {
    private PotRepository repository;

    public PotService(PotRepository repository){
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
