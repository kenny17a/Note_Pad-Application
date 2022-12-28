package com.notepaddiary.notepad.data.repository;

import com.notepaddiary.notepad.data.models.Entries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntriesRepository extends JpaRepository<Entries, Integer> {
    Optional<Entries> findEntryByTitle(String title);
    Optional<Entries> findEntryById(int id);


}
