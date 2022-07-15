package com.autentia.pot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autentia.pot.model.Friend;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findAll();
    Friend findFriendById(Long id);

}
