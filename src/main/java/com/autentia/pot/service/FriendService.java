package com.autentia.pot.service;

import com.autentia.pot.model.Friend;
import com.autentia.pot.repository.FriendRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {
    @Autowired
    private FriendRepository repository;

    public void addFriend(Friend friend){
        repository.save(friend);
    }

    public List<Friend> getAllUsers(){
        return repository.findAll();
    }

    public Friend getFriendBy(Long id){
        return repository.findFriendById(id);
    }

    public void deleteFriendBy(Long id){
        if (repository.findFriendById(id) != null)
            repository.deleteById(id);
    }
}
