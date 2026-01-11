package com.ai_study_assist.ai_study_assistant.controller;

import com.ai_study_assist.ai_study_assistant.entity.Notes;
import com.ai_study_assist.ai_study_assistant.entity.User;
import com.ai_study_assist.ai_study_assistant.repository.NotesRepository;
import com.ai_study_assist.ai_study_assistant.repository.UserRepository;
import com.ai_study_assist.ai_study_assistant.response.NotesSummaryResponse;
import com.ai_study_assist.ai_study_assistant.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user/notes")
public class NotesController {

    @Autowired
    NotesService notesService;
    @Autowired
    UserRepository userRepository;



    @PostMapping("/addNotes")
    public Notes addNotes(@RequestBody Notes notes){
        Long defaultUserId =  getCurrentUserId();
        return notesService.addNotes(defaultUserId,notes);
    }

    public  Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user.getId();

    }

    @DeleteMapping("/delete/{notesId}")
    public void deleteNotes(@PathVariable Long notesId){
        Long userId =  getCurrentUserId();

        notesService.deleteNotes(userId,notesId);
    }

    @PutMapping("/update/{notesId}")
    public Notes updateNotes(@PathVariable Long notesId, @RequestBody Notes notesDetails){
        Notes notesOptional = notesService.getNoteById(notesId);
            notesOptional.setTitle(notesDetails.getTitle());
            notesOptional.setContent(notesDetails.getContent());

        return notesService.updateNotes(notesOptional);
    }

    @GetMapping("/getMyNotes")
    public List<Notes> getMyNotes(){
        Long defaultUserId =  getCurrentUserId();

        return notesService.findAllNotes(defaultUserId);
    }


    @PostMapping("/summary/{notesId}")
    public ResponseEntity<NotesSummaryResponse> generateSummary
            (@PathVariable Long notesId){
        Long userId =  getCurrentUserId();
        NotesSummaryResponse notesSummaryResponse = notesService.generateSummary(notesId,userId);
        return ResponseEntity.ok(notesSummaryResponse);

    }

}
