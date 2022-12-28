package com.notepaddiary.notepad.data.dtos.request;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
}
