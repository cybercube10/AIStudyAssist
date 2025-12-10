package com.ai_study_assist.ai_study_assistant.controller;

import com.ai_study_assist.ai_study_assistant.entity.User;
import com.ai_study_assist.ai_study_assistant.response.LoginRequest;
import com.ai_study_assist.ai_study_assistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/register")
    public User addUser(@RequestBody User user){
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        user.setPassword(encoder.encode(user.getPassword()));
       return userService.addUser(user);
       
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
      return userService.loginUser(loginRequest);
    }
}
