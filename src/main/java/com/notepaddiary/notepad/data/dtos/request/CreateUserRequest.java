package com.notepaddiary.notepad.data.dtos.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String noteName;
    private String noteEntries;
}
