package com.notepaddiary.notepad.data.controllers;

import com.notepaddiary.notepad.data.dtos.request.CreateUserRequest;
import com.notepaddiary.notepad.data.dtos.request.UpdateNoteRequest;
import com.notepaddiary.notepad.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/note")
public class NotesController {
    @Autowired
    private NotesService notesService;

    @PostMapping
    public ResponseEntity<?> createNote(@RequestBody CreateUserRequest createUserRequest){
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(notesService.createNote(createUserRequest));
    }
    @PatchMapping
    public ResponseEntity<?> updateNote(@RequestBody UpdateNoteRequest updateNoteRequest){
        return ResponseEntity.ok(notesService.updateNote(updateNoteRequest));
    }
    @DeleteMapping
    public ResponseEntity<?> deleteNote(int id){
        return ResponseEntity.ok(notesService.deleteNote(id));
    }
}
