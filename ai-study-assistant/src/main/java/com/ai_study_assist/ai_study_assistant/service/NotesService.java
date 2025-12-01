package com.ai_study_assist.ai_study_assistant.service;

import com.ai_study_assist.ai_study_assistant.entity.Notes;
import com.ai_study_assist.ai_study_assistant.entity.User;
import com.ai_study_assist.ai_study_assistant.repository.NotesRepository;
import com.ai_study_assist.ai_study_assistant.repository.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class NotesService {
    @Autowired
    private NotesRepository notesRepository;
    @Autowired
    private UserRepository userRepository;



    public Notes addNotes(Long userId, Notes note){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        note.setUser(user);
        return notesRepository.save(note);
    }



    public void deleteNotes(Long userId,Long NotesId){
        Notes note = notesRepository.findByIdAndUserId(NotesId, userId)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        notesRepository.delete(note);
    }

    public List<Notes> findAllNotes(Long userId){
        List<Notes> notesList = notesRepository.findByUserId(userId);
        if(notesList.isEmpty()){
            throw new RuntimeException("No notes found for this user");
        }
        return notesList;
    }

    public Notes updateNotes(Notes updateNote){
        return notesRepository.save(updateNote);
    }

    public Notes getNoteById(Long noteId){
        return notesRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }
}
