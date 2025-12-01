package com.ai_study_assist.ai_study_assistant.repository;

import com.ai_study_assist.ai_study_assistant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByUsername(String username);

}
