package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.model.User;
import com.example.project.model.UserDTO;
import com.example.project.repository.FriendRepository;
import com.example.project.repository.UserDTORepository;

@Service
public class UserService {

    @Autowired
    private UserDTORepository userDto;

    public UserDTO getOne(Integer id) {
        return userDto.getOne(id);
    }

    public UserDTO addUser(User user) {
        UserDTO tempUser = new UserDTO(user.getUserId(),user.getUserName(),user.getEmailId(),user.getTotalBalance(),user.getBalanceStatus());
        return userDto.save(tempUser);
    }

    public UserDTO deleteUserById(Integer id) {

        UserDTO tempUser = getOne(id);
        if( tempUser!= null){
             userDto.delete(tempUser);
             return tempUser;
        }
        return null;
    }

    public UserDTO updateUser(User user) {
        UserDTO tempUser = getOne(user.getUserId());
        if( tempUser!= null){
            tempUser.setUserName(user.getUserName());
            tempUser.setBalanceStatus(user.getBalanceStatus());
            tempUser.setEmailId(user.getEmailId());
            tempUser.setTotalBalance(user.getTotalBalance());
            return userDto.save(tempUser);
        }
        tempUser = new UserDTO(user.getUserId(),user.getUserName(),user.getEmailId(),user.getTotalBalance(),user.getBalanceStatus());
        return userDto.save(tempUser);
    }
}
