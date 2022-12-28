package com.notepaddiary.notepad.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private String email;
    @OneToOne
    private Notes note;
}
