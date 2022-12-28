package com.notepaddiary.notepad.services;

import com.notepaddiary.notepad.data.dtos.request.CreateUserRequest;
import com.notepaddiary.notepad.data.dtos.request.LoginRequest;
import com.notepaddiary.notepad.data.dtos.request.UpdateRequest;
import com.notepaddiary.notepad.data.dtos.request.UserRegisterRequest;
import com.notepaddiary.notepad.data.dtos.response.CreateUserResponse;
import com.notepaddiary.notepad.data.dtos.response.LoginResponse;
import com.notepaddiary.notepad.data.dtos.response.Response;
import com.notepaddiary.notepad.data.dtos.response.UserRegisterResponse;
import com.notepaddiary.notepad.data.models.User;

import java.util.List;

public interface UserService {
    UserRegisterResponse createUser(UserRegisterRequest userRegisterRequest);
    LoginResponse login(LoginRequest loginRequest);
    Response updateUser(UpdateRequest updateRequest);
    Response deleteUser(int id);
    List<User> getAllUserFromDb();
}
