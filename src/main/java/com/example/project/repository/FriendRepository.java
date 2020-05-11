package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Friend;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer>{

	
}
