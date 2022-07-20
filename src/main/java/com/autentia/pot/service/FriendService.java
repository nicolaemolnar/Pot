package com.autentia.pot.service;

import com.autentia.pot.model.Friend;
import com.autentia.pot.repository.FriendRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {
    private FriendRepository repository;

    public FriendService(FriendRepository repository){
        this.repository = repository;
    }

    public void addFriend(Friend friend){
        repository.save(friend);
    }

    public List<Friend> getAllUsers(){
        return repository.findAll();
    }

    public Friend getFriendBy(Long id){
        return repository.findFriendById(id);
    }
}
