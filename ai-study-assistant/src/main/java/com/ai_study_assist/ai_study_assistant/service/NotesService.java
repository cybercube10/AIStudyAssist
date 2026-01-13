package com.ai_study_assist.ai_study_assistant.service;

import com.ai_study_assist.ai_study_assistant.entity.Notes;
import com.ai_study_assist.ai_study_assistant.entity.User;
import com.ai_study_assist.ai_study_assistant.exceptions.ResourceNotFoundException;
import com.ai_study_assist.ai_study_assistant.repository.NotesRepository;
import com.ai_study_assist.ai_study_assistant.repository.UserRepository;
import com.ai_study_assist.ai_study_assistant.response.NotesSummaryResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;
    @Autowired
    private UserRepository userRepository;


    private final RestTemplate restTemplate;
    @Autowired
    public NotesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public Notes addNotes(Long userId, Notes note){

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        try{note.setUser(user);
            return notesRepository.save(note);
        }
        catch(Exception e){

            e.printStackTrace();
            return null;
        }
    }



    public void deleteNotes(Long userId,Long NotesId){
        Notes note = notesRepository.findByIdAndUserId(NotesId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));
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
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));
    }


    public NotesSummaryResponse generateSummary(Long notesId,Long userId) {
        Notes note = notesRepository.findByIdAndUserId(notesId, userId).orElseThrow(() -> new ResourceNotFoundException("Note not found"));
        Map<String,Object> requestBody = new HashMap<>();

        requestBody.put("title", note.getTitle());
        requestBody.put("content", note.getContent());
        Map<String, Object> response = restTemplate.postForObject(
                "http://127.0.0.1:5000/",
                requestBody,
                Map.class
        );


        return  NotesSummaryResponse.builder()
               .notesId(notesId)
               .userId(userId)
               .noteTitle((String) response.get("title"))
               .noteContent((String) response.get("summary"))
               .practiceQuestions((List<String>) response.get("practice_questions")).build();

    }
}
