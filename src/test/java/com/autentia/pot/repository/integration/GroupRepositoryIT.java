package com.autentia.pot.repository.integration;

import com.autentia.pot.model.Group;
import com.autentia.pot.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupRepositoryIT {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    void shouldSaveGroup(){
        Group group = new Group();
        groupRepository.save(group);

        assertNotNull(groupRepository.findGroupById(group.getId()));
    }

    @Test
    void shouldFindGroupById() {
        Group expected_group = new Group();
        groupRepository.save(expected_group);

        Group group = groupRepository.findGroupById(expected_group.getId());

        assertEquals(expected_group, group);
    }
}