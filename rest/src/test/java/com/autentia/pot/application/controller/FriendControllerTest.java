package com.autentia.pot.application.controller;

import com.autentia.pot.core.service.FriendService;
import org.junit.jupiter.api.Test;
import com.autentia.pot.core.model.Friend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class FriendControllerTest {

    private FriendService service = mock(FriendService.class);

    @Test
    void getUsers() {
        Friend f = new Friend("G");
        service.addFriend(f);
        assertNotNull(f);
    }

    @Test
    void getUser() {
    }

    @Test
    void addUser() {
    }
}