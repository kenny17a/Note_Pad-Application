package com.notepaddiary.notepad.data.dtos.request;

import lombok.Data;

@Data
public class UpdateNoteRequest {
    private int noteId;
    private String noteName;
    private String noteEntries;
}
