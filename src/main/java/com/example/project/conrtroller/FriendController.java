package com.example.project.conrtroller;

import java.util.List;

import com.example.project.model.UserDTO;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.project.model.Friend;
import com.example.project.service.FriendService;

@RestController
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/friend/{id}", method = RequestMethod.GET)
    public ResponseEntity<Friend> getFriendById(@PathVariable("id") Integer id){
        Friend result = friendService.getFriendById(id);
        if(result == null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(result);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/friend", method = RequestMethod.POST)
    public ResponseEntity<Friend> addFriend(@RequestBody Friend friend){
        UserDTO user = userService.getOne(friend.getFriendId());
        user.getFriends().add(friend);
        return ResponseEntity.ok(friendService.addFriend(friend));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/friend/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Friend> deleteFriendById(@PathVariable("id") Integer id){
        Friend friend = friendService.getFriendById(id);
        if(friend != null){
            UserDTO user = userService.getOne(id);
            user.getFriends().clear();
            return ResponseEntity.ok(friendService.deleteFriendById(id));
        }
       return ResponseEntity.ok(null);

    }

}

