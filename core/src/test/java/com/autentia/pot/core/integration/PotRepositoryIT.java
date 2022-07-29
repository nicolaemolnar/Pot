package com.autentia.pot.core.integration;

import com.autentia.pot.core.model.Friend;
import com.autentia.pot.core.model.Pot;
import com.autentia.pot.core.repository.FriendRepository;
import com.autentia.pot.core.repository.PotRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@TransactionConfiguration(defaultRollback = true)
class PotRepositoryIT {

    @Autowired
    private PotRepository potRepository;

    @Autowired
    private FriendRepository friendRepository;

    @Test
    void shouldSaveGroup(){
        Pot pot = new Pot();
        potRepository.save(pot);

        assertNotNull(potRepository.findPotById(pot.getId()));
    }

    @Test
    void shouldFindGroupById() {
        Pot expected_pot = new Pot();
        potRepository.save(expected_pot);

        Pot pot = potRepository.findPotById(expected_pot.getId());

        assertEquals(expected_pot, pot);
    }

    @Test
    void shouldSaveGroupWithFriends() {
        List<Friend> saved_friends = new ArrayList<>();
        saved_friends.add(new Friend("Test 1"));
        saved_friends.add(new Friend("Test 2"));
        saved_friends.add(new Friend("Test 3"));

        friendRepository.saveAll(saved_friends);

        Pot expected_pot = new Pot("Test pot", saved_friends);
        potRepository.save(expected_pot);

        Pot pot = potRepository.findPotById(expected_pot.getId());

        assertEquals(expected_pot.getFriends(), pot.getFriends());
    }
}