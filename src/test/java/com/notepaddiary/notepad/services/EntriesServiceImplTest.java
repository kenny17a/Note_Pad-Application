package com.notepaddiary.notepad.services;

import com.notepaddiary.notepad.data.dtos.request.EntriesRequest;
import com.notepaddiary.notepad.data.dtos.request.RetrieveRequest;
import com.notepaddiary.notepad.data.dtos.response.EntriesRetrieveResponse;
import com.notepaddiary.notepad.data.dtos.response.Response;
import com.notepaddiary.notepad.data.dtos.response.WriteResponse;
import com.notepaddiary.notepad.data.models.Entries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EntriesServiceImplTest {
    @Autowired
    private EntriesService entriesService;
    private EntriesRequest entriesRequest;

    @BeforeEach
    void setUp() {

    }
    @Test
    void writeToEntries(){
        entriesRequest = new EntriesRequest();
        entriesRequest.setLocalDateTime(LocalDateTime.now());
        entriesRequest.setTitle("Corruption in Nigeria");
        entriesRequest.setBody("Corruption runs through every level of Nigerian government.");
        WriteResponse writeResponse = entriesService.writeInEntries(entriesRequest);
        assertNotNull(writeResponse);
        assertEquals("Your entry has been saved", writeResponse.getMessage());
    }
    @Test
    void updateEntries(){
        EntriesRequest updatingEntry = new EntriesRequest();
        updatingEntry.setTitle("Information and Communications Technology");
        updatingEntry.setBody("Nigeria is regarded as Africa’s largest ICT market with 82% of the continent’s telecoms subscribers and 29% of internet usage. " +
                "Sub-Saharan Africa is also projected to be the fastest growing region with a compound annual growth rate (CAGR) of 4.6%");
        updatingEntry.setId(1);
        updatingEntry.setLocalDateTime(LocalDateTime.now());
        Entries newEntry = new Entries();
        newEntry.setTitle(updatingEntry.getTitle());
        newEntry.setBody(updatingEntry.getBody());
        newEntry.setDate(updatingEntry.getLocalDateTime().format(DateTimeFormatter.ofPattern("dd:MM:yy, hh:mm")));
        Response response = entriesService.updateEntries(updatingEntry);
        assertEquals("Entry Updated successfully", response.getMessage());
    }
    @Test
    void retrieveEntries(){
        RetrieveRequest retrieveEntry = new RetrieveRequest();
        retrieveEntry.setTitle("Corruption in Nigeria");
        EntriesRetrieveResponse retrieveResponse = entriesService.retrieveEntries(retrieveEntry);
        assertEquals("Entry has been found", retrieveResponse.getMessage());
    }
    @Test
    void deleteEntries(){
        Response deleteResponse = entriesService.deleteEntries(52);
        assertEquals("Entry deleted successfully", deleteResponse.getMessage());
    }
    @Test
    void deleteAllEntries(){
        Response deleteAllResponses = entriesService.deleteAllEntries();
        assertEquals("All Entries successfully deleted", deleteAllResponses.getMessage());
    }

}