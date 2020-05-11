package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.model.Friend;
import com.example.project.model.UserDTO;
import com.example.project.repository.FriendRepository;
import com.example.project.repository.UserDTORepository;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    public Friend getFriendById(Integer id) {
        return friendRepository.getOne(id);
    }

    public Friend addFriend(Friend friend) {
        return friendRepository.save(friend);
    }

    public Friend deleteFriendById(Integer id) {
        Friend friend = friendRepository.getOne(id);
        if(friend!= null){
            friendRepository.delete(friend);
            return friend;
        }
        return null;
    }
}
