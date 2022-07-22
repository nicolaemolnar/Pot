package com.autentia.pot.service.unit;

import com.autentia.pot.model.Friend;
import com.autentia.pot.repository.FriendRepository;
import com.autentia.pot.service.FriendService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FriendServiceTest {

    private final FriendRepository repository = mock(FriendRepository.class);
    private final FriendService service = new FriendService(repository);

    @Test
    void shouldAddFriend() {
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

        assertEquals(expected_friends, friends);
    }

    @Test
    void shouldGetFriendBy() {
        Long friendId = 2L;
        Friend expected_friend = new Friend(friendId, "Test friend");
        when(repository.findFriendById(friendId)).thenReturn(expected_friend);

        Friend friend = service.getFriendBy(friendId);

        assertEquals(expected_friend, friend);
    }
}