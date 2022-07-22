package com.autentia.pot.service.unit;

import com.autentia.pot.model.Friend;
import com.autentia.pot.model.Group;
import com.autentia.pot.repository.GroupRepository;
import com.autentia.pot.service.GroupService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GroupServiceTest {

    private final GroupRepository repository = mock(GroupRepository.class);
    private final GroupService service = new GroupService(repository);


    @Test
    void shouldGetAllGroups() {
        List<Group> expected_groups = new ArrayList<>();
        expected_groups.add(new Group(1L));
        expected_groups.add(new Group(2L));
        expected_groups.add(new Group(3L));
        when(repository.findAll()).thenReturn(expected_groups);

        List<Group> groups = service.getAllGroups();

        assertEquals(expected_groups, groups);
    }

    @Test
    void shouldAddGroup() {
        Group group = new Group(1L);
        service.addGroup(group);
        verify(repository).save(group);
    }

    @Test
    void shouldAddFriendToGroup() {
        Friend friend = new Friend("Testing subject");
        Group group = new Group(2L);
        List<Friend> expected_friends = new ArrayList<>();
        expected_friends.add(friend);

        service.addFriendToGroup(friend, group);
        assertEquals(expected_friends, group.getFriends());
    }

    @Test
    void shouldGetGroupBy() {
        Long groupId = 1L;

        when(repository.findGroupById(groupId)).thenReturn(new Group(groupId));

        Group group = service.getGroupBy(groupId);
        assertEquals(new Group(groupId), group);
    }
}