package com.example.WithRun.controller;

import com.example.WithRun.domain.User;
import com.example.WithRun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> home(){
        return ResponseEntity.ok().body("WITHRUN HOMEPAGE");
    }

    @GetMapping("/search/user")
    public ResponseEntity<?> searchUser(@RequestHeader String username){
        User findUser = userService.getUserByUsername(username);
        if(findUser==null){
            return ResponseEntity.badRequest().body("Cannot find user.");
        }
        return ResponseEntity.ok().body(userService.getUserByUsername(username));
    }


}
