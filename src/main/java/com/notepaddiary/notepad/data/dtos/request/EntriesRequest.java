package com.notepaddiary.notepad.data.dtos.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EntriesRequest {
    private int id;
    public String title;
    private String body;
    private LocalDateTime localDateTime;
}
