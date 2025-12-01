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
        try{
            return userRepository.save(user);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public String loginUser(User user){
    Authentication authentication = authmanager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    if(authentication.isAuthenticated()){
        return jwtService.generateToken(user.getUsername());
    }
    return "Unable to Login";
    }



}
