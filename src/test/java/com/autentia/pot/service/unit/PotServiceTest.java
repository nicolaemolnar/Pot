package com.autentia.pot.service.unit;

import com.autentia.pot.model.Friend;
import com.autentia.pot.model.Pot;
import com.autentia.pot.repository.PotRepository;
import com.autentia.pot.service.PotService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PotServiceTest {

    private final PotRepository repository = mock(PotRepository.class);
    private final PotService service = new PotService(repository);


    @Test
    void shouldGetAllPots() {
        List<Pot> expected_pots = new ArrayList<>();
        expected_pots.add(new Pot(1L));
        expected_pots.add(new Pot(2L));
        expected_pots.add(new Pot(3L));
        when(repository.findAll()).thenReturn(expected_pots);

        List<Pot> pots = service.getAllPots();

        assertEquals(expected_pots, pots);
    }

    @Test
    void shouldAddPot() {
        Pot pot = new Pot(1L);
        service.addPot(pot);
        verify(repository).save(pot);
    }

    @Test
    void shouldAddFriendToPot() {
        Friend friend = new Friend("Testing subject");
        Pot pot = new Pot(2L);
        List<Friend> expected_friends = new ArrayList<>();
        expected_friends.add(friend);

        service.addFriendToPot(friend, pot);
        assertEquals(expected_friends, pot.getFriends());
    }

    @Test
    void shouldGetPotBy() {
        Long potId = 1L;

        when(repository.findPotById(potId)).thenReturn(new Pot(potId));

        Pot pot = service.getPotById(potId);
        assertEquals(new Pot(potId), pot);
    }
}