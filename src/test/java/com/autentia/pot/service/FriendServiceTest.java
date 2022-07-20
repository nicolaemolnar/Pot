package com.autentia.pot.service;

import com.autentia.pot.model.Friend;
import com.autentia.pot.repository.FriendRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FriendServiceTest {

    private final FriendRepository repository = mock(FriendRepository.class);
    private final FriendService service = new FriendService(repository);

    @Test
    void addFriend() {
        Friend friend = new Friend("Test user");
        service.addFriend(friend);
        verify(repository).save(friend);
    }

    @Test
    void shouldGetAllUsers() {
        List<Friend> expected_friends = new ArrayList<>();
        expected_friends.add(new Friend("Lucía Rodriguez"));
        expected_friends.add(new Friend("Juan Carlos Moreno"));
        expected_friends.add(new Friend("Nicolae Alexandru Molnar"));
        when(repository.findAll()).thenReturn(expected_friends);

        List<Friend> friends = service.getAllUsers();

        verify(repository).findAll();
        assertEquals(expected_friends, friends);
    }

    @Test
    void getFriendBy() {
        Long friendId = 2L;
        Friend expected_friend = new Friend(friendId, "Test friend");
        when(repository.findFriendById(friendId)).thenReturn(expected_friend);

        Friend friend = service.getFriendBy(friendId);

        verify(repository).findFriendById(friendId);
        assertEquals(expected_friend, friend);
    }

    @Test
    void deleteFriendBy() {
        Long existingId = 1L;
        Long nonExistingId = 2L;
        when(repository.findFriendById(existingId)).thenReturn(new Friend(existingId, "Test"));
        when(repository.findFriendById(nonExistingId)).thenReturn(null);

        service.deleteFriendBy(existingId);
        service.deleteFriendBy(nonExistingId);

        verify(repository).findFriendById(existingId);
        verify(repository).deleteById(existingId);
        verify(repository).findFriendById(nonExistingId);
        verify(repository, times(0)).deleteById(nonExistingId);
    }
}