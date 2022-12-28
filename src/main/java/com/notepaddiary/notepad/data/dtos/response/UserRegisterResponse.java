package com.notepaddiary.notepad.data.dtos.response;

import lombok.Data;

@Data
public class UserRegisterResponse {
    private int id;
    private String message;
    private int statusCode;
}
