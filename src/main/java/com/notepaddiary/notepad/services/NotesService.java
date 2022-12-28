package com.notepaddiary.notepad.services;

import com.notepaddiary.notepad.data.dtos.request.CreateUserRequest;
import com.notepaddiary.notepad.data.dtos.request.EntriesRequest;
import com.notepaddiary.notepad.data.dtos.request.UpdateNoteRequest;
import com.notepaddiary.notepad.data.dtos.response.CreateUserResponse;
import com.notepaddiary.notepad.data.dtos.response.Response;
import com.notepaddiary.notepad.data.models.Notes;

import java.util.Optional;

public interface NotesService {
    CreateUserResponse createNote(CreateUserRequest writeRequest);
    Response updateNote(UpdateNoteRequest updateNoteRequest);
    Response deleteNote(int id);
    Optional<Notes> getNotesById(int id);
    Response addEntryToNote(EntriesRequest entriesRequest);

}
