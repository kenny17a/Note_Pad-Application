package com.notepaddiary.notepad.services;

import com.notepaddiary.notepad.data.dtos.request.EntriesRequest;
import com.notepaddiary.notepad.data.dtos.request.RetrieveRequest;
import com.notepaddiary.notepad.data.dtos.response.EntriesRetrieveResponse;
import com.notepaddiary.notepad.data.dtos.response.WriteResponse;
import com.notepaddiary.notepad.data.models.Entries;
import com.notepaddiary.notepad.data.dtos.response.Response;
import com.notepaddiary.notepad.data.repository.EntriesRepository;
import com.notepaddiary.notepad.validators.UserEntryValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class EntriesServiceImpl implements EntriesService{

    @Autowired
    private EntriesRepository entriesRepository;

    @Override
    public WriteResponse writeInEntries(EntriesRequest writeRequest) {
        if (!UserEntryValidators.isValidateEntryTitle(writeRequest.getTitle()))
            throw new RuntimeException("Title should not be more than 200");
        if (!UserEntryValidators.isValidateEntryBody(writeRequest.getBody()))
            throw new RuntimeException("Character limit exceeded");
        Entries freshEntries = newCreatedEntries(writeRequest);
        Entries savedEntries = entriesRepository.save(freshEntries);

        return newCreatedEntryResponse(savedEntries);
    }

    private Entries newCreatedEntries(EntriesRequest writeRequest) {
        Entries freshEntries = new Entries();
        freshEntries.setTitle(writeRequest.getTitle());
        freshEntries.setBody(writeRequest.getBody());
        freshEntries.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yy, HH:mm")));
        return freshEntries;
    }

    private WriteResponse newCreatedEntryResponse(Entries savedEntries) {
        WriteResponse writeResponse = new WriteResponse();
        writeResponse.setMessage("Your entry has been saved");
        writeResponse.setStatusCode(201);
        writeResponse.setId(savedEntries.getId());
        return writeResponse;
    }

    @Override
    public EntriesRetrieveResponse retrieveEntries(RetrieveRequest retrieveRequest) {
        Entries entryRetrieve = entriesRepository.findEntryByTitle(retrieveRequest.getTitle())
                .orElseThrow(() -> new RuntimeException("Entry has not available "));
        EntriesRetrieveResponse retrieveResponse = new EntriesRetrieveResponse();

        retrieveResponse.setMessage("Entry has been found");
        return retrieveResponse;
    }

    @Override
    public Response updateEntries(EntriesRequest updateEntriesRequest) {
        Optional<Entries> updateEntries = entriesRepository.findEntryById(updateEntriesRequest.getId());
        if(updateEntries.isEmpty()) throw  new RuntimeException("Entry not found");
        Entries foundEntries = UpdatingEntries(updateEntriesRequest, updateEntries);
        entriesRepository.save(foundEntries);
        return new Response("Entry Updated successfully");
    }

    private Entries UpdatingEntries(EntriesRequest updateEntriesRequest, Optional<Entries> updateEntries) {
        Entries foundEntries = updateEntries.get();
        foundEntries.setTitle(updateEntriesRequest.getTitle() != null && !updateEntriesRequest.getTitle()
                .equals("") ? updateEntriesRequest.getTitle() : foundEntries.getTitle());
        foundEntries.setBody(updateEntriesRequest.getBody() != null && !updateEntriesRequest.getBody()
                .equals("") ? updateEntriesRequest.getBody() : foundEntries.getBody());
        return foundEntries;
    }

    @Override
    public Response deleteEntries(int id) {
        entriesRepository.deleteById(id);
        return new Response("Entry deleted successfully");
    }

    @Override
    public List<Entries> getAllEntries() {
        return entriesRepository.findAll();
    }


    @Override
    public Response deleteAllEntries() {
        entriesRepository.deleteAll();
        return new Response("All Entries successfully deleted");

    }
}
