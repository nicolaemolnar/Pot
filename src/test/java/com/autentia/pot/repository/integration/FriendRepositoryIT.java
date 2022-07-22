package com.autentia.pot.repository.integration;

import com.autentia.pot.model.Friend;
import com.autentia.pot.repository.FriendRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FriendRepositoryIT {

    @Autowired
    private FriendRepository repository;

    @Test
    void shouldSaveFriend() {
        Friend friend = new Friend("Test user");
        repository.save(friend);
        Friend result = repository.findFriendById(friend.getId());

        assertEquals(friend, result);
    }

    @Test
    void shouldFindAllFriends() {
        List<Friend> friends = repository.findAll();
        long nFriends = repository.count();

        assertEquals(nFriends, friends.size());
    }

    @Test
    void shouldFindFriendBy() {
        Friend expected_friend = new Friend("Paul");
        repository.save(expected_friend);

        Friend friend = repository.findFriendById(expected_friend.getId());

        assertEquals(expected_friend, friend);
    }

}