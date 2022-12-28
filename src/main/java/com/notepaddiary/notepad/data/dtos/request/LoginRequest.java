package com.notepaddiary.notepad.data.dtos.request;

import com.notepaddiary.notepad.data.dtos.response.LoginResponse;
import com.notepaddiary.notepad.data.models.User;
import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;

}
