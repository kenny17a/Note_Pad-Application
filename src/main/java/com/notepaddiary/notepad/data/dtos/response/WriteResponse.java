package com.notepaddiary.notepad.data.dtos.response;

import lombok.Data;

@Data
public class WriteResponse {
    private int id;
    private int statusCode;
    private String message;
}
