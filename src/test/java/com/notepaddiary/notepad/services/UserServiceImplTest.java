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
import com.notepaddiary.notepad.data.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    private UserRegisterRequest userRegisterRequest;

    @BeforeEach
    void setUp() {
        userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setFirstName("Kenny");
        userRegisterRequest.setLastName("Ibrahim");
        userRegisterRequest.setEmail("ibrahimkenny@gmail.com");
        userRegisterRequest.setPhoneNumber("08130827909");
        userRegisterRequest.setPassword("Kehinde@123");
    }
    @Test
    void testThatUserCanBeCreated(){


        UserRegisterResponse userRegisterResponse = userService.createUser(userRegisterRequest);
        assertNotNull(userRegisterResponse);
        assertEquals("User Successfully created", userRegisterResponse.getMessage());
    }
    @Test
    void testThatUserCanLogin(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(userRegisterRequest.getEmail());
        Optional<User> findUser = userRepository.findUserByEmail(loginRequest.getEmail());
        assertNotNull(findUser);
        loginRequest.setPassword(userRegisterRequest.getPassword());

        LoginResponse loginResponse = userService.login(loginRequest);
        assertEquals("Login was Successful", loginResponse.getMessage());
    }

    @Test
    void testThatUserCanBeUpdated(){


        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setFirstName("Invincible");
        updateRequest.setLastName("Cole");
        updateRequest.setPhoneNumber("09165098649");
        updateRequest.setId(1);
        updateRequest.setEmail("oluwatomisin@gmail.com");
        updateRequest.setPassword("oluwatomisin@123");
        Response newResponse = userService.updateUser(updateRequest);
        assertEquals("User Details Updated Successfully", newResponse.getMessage());
    }
    @Test
    void testThatUserCanBeDeleted(){
        Response normalResponse = userService.deleteUser(2);
        assertEquals("User deleted", normalResponse.getMessage());
    }

}