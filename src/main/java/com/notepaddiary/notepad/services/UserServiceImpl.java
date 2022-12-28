package com.notepaddiary.notepad.services;

import com.notepaddiary.notepad.data.dtos.request.LoginRequest;
import com.notepaddiary.notepad.data.dtos.request.UpdateRequest;
import com.notepaddiary.notepad.data.dtos.request.UserRegisterRequest;
import com.notepaddiary.notepad.data.dtos.response.CreateUserResponse;
import com.notepaddiary.notepad.data.dtos.response.LoginResponse;
import com.notepaddiary.notepad.data.dtos.response.Response;
import com.notepaddiary.notepad.data.dtos.response.UserRegisterResponse;
import com.notepaddiary.notepad.data.models.User;
import com.notepaddiary.notepad.data.repository.UserRepository;
import com.notepaddiary.notepad.validators.UserValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserRegisterResponse createUser(UserRegisterRequest userRegisterRequest) {

        if(!UserValidators.isValidPassword(userRegisterRequest.getPassword()))
        throw new RuntimeException(String.format("%s Incorrect Password", userRegisterRequest.getPassword()));


        if(!UserValidators.isValidPhoneNumber(userRegisterRequest.getPhoneNumber()))
        throw new RuntimeException(String.format("%s Wrong PhoneNumber", userRegisterRequest.getPhoneNumber()));

        if(!UserValidators.isValidEmailAddress(userRegisterRequest.getEmail()))
        throw new RuntimeException(String.format("%s Wrong Email", userRegisterRequest.getEmail()));

        User user = registrationPointForUser(userRegisterRequest);

        User savedUser = userRepository.save(user);


        return newUserRegisterResponse(savedUser);
    }


    private UserRegisterResponse newUserRegisterResponse(User savedUser) {
        UserRegisterResponse userRegisterResponse= new UserRegisterResponse();
        userRegisterResponse.setMessage("User Successfully created");
        userRegisterResponse.setStatusCode(201);
        userRegisterResponse.setId(savedUser.getId());
        return userRegisterResponse;

    }

    private User registrationPointForUser(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setPassword(userRegisterRequest.getPassword());
        user.setPhoneNumber(userRegisterRequest.getPhoneNumber());
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        user.setEmail(userRegisterRequest.getEmail());
        return user;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User findUser = userRepository.findUserByEmail(
                loginRequest.getEmail()).orElseThrow(()->new RuntimeException("Email not found"));

        LoginResponse loginResponse = new LoginResponse();
        if (findUser.getPassword().equals(loginRequest.getPassword())) {
            loginResponse.setMessage("Login was Successful");
            return loginResponse;
        }else {
            loginResponse.setMessage("Login Not Successful");
        }

        return loginResponse;
    }

    @Override
    public Response updateUser(UpdateRequest updateRequest) {
        var user = userRepository.findById(updateRequest.getId());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        UpdatedRegisteredUser(updateRequest, user);
        return new Response("User Details Updated Successfully");
    }

    public void UpdatedRegisteredUser (UpdateRequest updateRequest, Optional<User> user) {
        User foundUser = user.get();
        foundUser.setFirstName(updateRequest.getFirstName() != null && !updateRequest.getFirstName().equals("")
        ? updateRequest.getFirstName() : foundUser.getFirstName());
        foundUser.setLastName(updateRequest.getLastName() != null && !updateRequest.getLastName().equals("")
        ? updateRequest.getLastName() : foundUser.getLastName());
        foundUser.setEmail(updateRequest.getEmail() != null && !updateRequest.getEmail().equals("")
        ? updateRequest.getEmail() : foundUser.getEmail());
        foundUser.setPhoneNumber(updateRequest.getPhoneNumber() != null && !updateRequest.getPhoneNumber().equals("")
        ? updateRequest.getPhoneNumber() : foundUser.getPhoneNumber());
        foundUser.setPassword(updateRequest.getPassword() != null && !updateRequest.getPassword().equals("")
        ? updateRequest.getPassword() : foundUser.getPassword());
         userRepository.save(foundUser);
    }

    @Override
    public Response deleteUser(int id) {
        userRepository.deleteById(id);
        return new Response("User deleted");
    }

    @Override
    public List<User> getAllUserFromDb() {
        return userRepository.findAll();
    }

}
