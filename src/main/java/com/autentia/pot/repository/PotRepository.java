package com.autentia.pot.repository;

import com.autentia.pot.model.Pot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PotRepository extends JpaRepository<Pot, Long> {
    Pot findPotById(Long potId);
}
