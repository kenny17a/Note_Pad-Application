package com.notepaddiary.notepad.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Entries> entries = new HashSet<>();
}
