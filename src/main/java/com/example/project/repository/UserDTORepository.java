package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.UserDTO;

@Repository
public interface UserDTORepository extends JpaRepository<UserDTO, Integer> {

}
