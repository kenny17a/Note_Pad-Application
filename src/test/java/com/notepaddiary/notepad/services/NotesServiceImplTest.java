package com.notepaddiary.notepad.services;

import com.notepaddiary.notepad.data.dtos.request.CreateUserRequest;
import com.notepaddiary.notepad.data.dtos.request.EntriesRequest;
import com.notepaddiary.notepad.data.dtos.request.UpdateNoteRequest;
import com.notepaddiary.notepad.data.dtos.response.CreateUserResponse;
import com.notepaddiary.notepad.data.dtos.response.Response;
import com.notepaddiary.notepad.data.repository.NotesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NotesServiceImplTest {
    @Autowired
    private NotesRepository notesRepository;
    @Autowired
    private NotesService notesService;

    private CreateUserRequest createNotePage;
    @BeforeEach
    void setUp() {
        createNotePage = new CreateUserRequest();
        createNotePage.setNoteName("programming is Logical");
    }
    @Test
    void createNewNote(){
        CreateUserResponse createUserResponse = notesService.createNote(createNotePage);
        assertNotNull(createUserResponse);
        assertEquals("Notes created successfully", createUserResponse.getMessage());
    }
    @Test
    void updateNote(){
        UpdateNoteRequest updateNoteRequest = new UpdateNoteRequest();
        updateNoteRequest.setNoteId(1);
        updateNoteRequest.setNoteName("SpringBoot NotePad App");
        Response updateNoteResponse = notesService.updateNote(updateNoteRequest);
        assertEquals("Notes has been updated", updateNoteResponse.getMessage());
    }
    @Test
    void addEntriesToNote(){
        EntriesRequest addEntryRequest = new EntriesRequest();
        addEntryRequest.setId(1);
        addEntryRequest.setTitle("New Page");
        addEntryRequest.setBody("Adding new entry to my notepad");
        Response response = notesService.addEntryToNote(addEntryRequest);
        assertEquals("New Page entry successfully added", response.getMessage());

    }

    @Test
    void deleteNote(){
        Response deleteResponse = notesService.deleteNote(1);
        assertNotNull(deleteResponse);
        assertEquals("Notes deleted successfully", deleteResponse.getMessage());
    }


}