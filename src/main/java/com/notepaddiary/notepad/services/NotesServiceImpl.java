package com.notepaddiary.notepad.services;

import com.notepaddiary.notepad.data.dtos.request.CreateUserRequest;
import com.notepaddiary.notepad.data.dtos.request.EntriesRequest;
import com.notepaddiary.notepad.data.dtos.request.UpdateNoteRequest;
import com.notepaddiary.notepad.data.dtos.response.CreateUserResponse;
import com.notepaddiary.notepad.data.dtos.response.Response;
import com.notepaddiary.notepad.data.models.Entries;
import com.notepaddiary.notepad.data.models.Notes;
import com.notepaddiary.notepad.data.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class NotesServiceImpl implements NotesService{

    @Autowired
    private NotesRepository notesRepository;
    @Override
   public CreateUserResponse createNote(CreateUserRequest createUserRequest) {
        Notes note = savedNotesResponse(createUserRequest);
        Notes savedNotes = notesRepository.save(note);

        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setId(savedNotes.getId());
        createUserResponse.setMessage("Notes created successfully");
        createUserResponse.setStatusCode(201);
        return createUserResponse;
    }

    @Override
    public Response updateNote(UpdateNoteRequest updateNoteRequest) {
        var findNote = notesRepository.findById(updateNoteRequest.getNoteId());
        if (findNote.isEmpty()) throw new RuntimeException("Note not found");
        Notes noteUpdate = findNote.get();
        noteUpdate.setId(updateNoteRequest.getNoteId());
        noteUpdate.setName(updateNoteRequest.getNoteName());
        notesRepository.save(noteUpdate);
        return new Response("Notes has been updated");
    }

    @Override
    public Response deleteNote(int id) {
        notesRepository.deleteById(id);
        return new Response("Notes deleted successfully");
    }

    @Override
    public Optional<Notes> getNotesById(int id) {
        return notesRepository.findById(id);
    }

    @Override
    public Response addEntryToNote(EntriesRequest entriesRequest) {
        Notes note = notesRepository.findById(entriesRequest.getId())
                .orElseThrow(() -> new RuntimeException("Note not found"));
        Entries entries = new Entries();
        entries.setBody(entriesRequest.getBody());
        entries.setTitle(entriesRequest.getTitle());
        entries.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yy, hh:mm")));
        note.getEntries().add(entries);
        notesRepository.save(note);
        return new Response("New Page entry successfully added");
    }


    private Notes savedNotesResponse(CreateUserRequest createUserRequest) {
        Notes note = new Notes();
        note.setName(createUserRequest.getNoteName());
        return note;
    }




}
