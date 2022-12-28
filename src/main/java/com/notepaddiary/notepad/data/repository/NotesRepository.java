package com.notepaddiary.notepad.data.repository;

import com.notepaddiary.notepad.data.models.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Notes, Integer> {
}
