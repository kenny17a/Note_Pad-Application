package com.notepaddiary.notepad.data.controllers;

import com.notepaddiary.notepad.data.dtos.request.CreateUserRequest;
import com.notepaddiary.notepad.data.dtos.request.UpdateRequest;
import com.notepaddiary.notepad.data.dtos.request.UserRegisterRequest;
import com.notepaddiary.notepad.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRegisterRequest userRegisterRequest){
       return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRegisterRequest));

    }
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return  ResponseEntity.ok(userService.getAllUserFromDb());
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUserById(@RequestParam int id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }
    @PatchMapping
    public ResponseEntity<?> updateUser(@RequestBody UpdateRequest updateRequest){
        return ResponseEntity.ok(userService.updateUser(updateRequest));
    }
}
