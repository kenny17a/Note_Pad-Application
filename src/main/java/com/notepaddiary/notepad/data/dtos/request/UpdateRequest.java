package com.notepaddiary.notepad.data.dtos.request;

import lombok.Data;

@Data
public class UpdateRequest {
    private int id;
    private String password;
    private String email;
    private String firstName;
    private String phoneNumber;
    private String lastName;
}
