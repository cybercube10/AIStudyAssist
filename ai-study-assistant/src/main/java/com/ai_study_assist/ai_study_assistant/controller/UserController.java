package com.ai_study_assist.ai_study_assistant.controller;

import com.ai_study_assist.ai_study_assistant.entity.User;
import com.ai_study_assist.ai_study_assistant.exceptions.UserAlreadyExists;
import com.ai_study_assist.ai_study_assistant.repository.UserRepository;
import com.ai_study_assist.ai_study_assistant.response.LoginRequest;
import com.ai_study_assist.ai_study_assistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
   @Autowired
   private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User user){
        if(userRepository.existsByEmailAndUsername(user.getEmail(),user.getUsername()))
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new UserAlreadyExists("User already exists"));
        }
        user.setPassword(encoder.encode(user.getPassword()));
        try {
            User saved = userService.addUser(user);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));

        }
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){

      return userService.loginUser(loginRequest);
    }
}
