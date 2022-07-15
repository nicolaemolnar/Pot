package com.autentia.pot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autentia.pot.model.Friend;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findAll();
    Friend findFriendById(Long id);

}
