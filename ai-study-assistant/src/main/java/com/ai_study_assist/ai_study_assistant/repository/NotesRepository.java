package com.ai_study_assist.ai_study_assistant.repository;

import com.ai_study_assist.ai_study_assistant.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotesRepository extends JpaRepository<Notes, Long> {
    Optional<Notes> findByIdAndUserId(Long id, Long userId);
    List<Notes> findByUserId(Long userId);



}
