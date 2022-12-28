package com.notepaddiary.notepad.data.controllers;

import com.notepaddiary.notepad.data.dtos.request.CreateUserRequest;
import com.notepaddiary.notepad.data.dtos.request.EntriesRequest;
import com.notepaddiary.notepad.data.dtos.request.RetrieveRequest;
import com.notepaddiary.notepad.services.EntriesService;
import com.notepaddiary.notepad.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/entries")
public class EntriesController {
    @Autowired
    private EntriesService entriesService;

    @PostMapping
    public ResponseEntity<?> writeInEntries(@RequestBody EntriesRequest writeRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(entriesService.writeInEntries((writeRequest)));
    }

    @PatchMapping
    public ResponseEntity<?> updateEntries(@RequestBody EntriesRequest updateRequest) {
        return ResponseEntity.ok(entriesService.updateEntries(updateRequest));
    }

    @GetMapping
    public ResponseEntity<?> retrieveEntries(@RequestBody RetrieveRequest retrieveRequest) {
        return ResponseEntity.ok(entriesService.retrieveEntries(retrieveRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntries(@PathVariable int id) {
        return ResponseEntity.ok(entriesService.deleteEntries(id));
    }

}
