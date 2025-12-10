package com.ai_study_assist.ai_study_assistant.service;

import com.ai_study_assist.ai_study_assistant.entity.User;
import com.ai_study_assist.ai_study_assistant.entity.UserPrincipal;
import com.ai_study_assist.ai_study_assistant.repository.UserRepository;
import com.ai_study_assist.ai_study_assistant.response.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
   @Autowired
    AuthenticationManager authmanager;

   @Autowired
   JWTService jwtService;

    public User addUser(User user){
    if(userRepository.findByUsername(user.getUsername())!=null){
        throw new RuntimeException("User with this username already exists");
    }
    if(userRepository.findByEmail(user.getEmail())!=null){
        throw new RuntimeException("User with this email already exists");
    }

            return userRepository.save(user);
    }

        public String loginUser(LoginRequest user){
        try {
            Authentication authentication = authmanager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            if(authentication.isAuthenticated()){
                return jwtService.generateToken(user.getUsername());
            } else {
                System.out.println("Authentication returned false!");
            }
        } catch (org.springframework.security.core.AuthenticationException ex) {
            System.out.println("Authentication failed: " + ex.getClass().getSimpleName() + " - " + ex.getMessage());
        }
        return "Authentication failed";
    }




}
