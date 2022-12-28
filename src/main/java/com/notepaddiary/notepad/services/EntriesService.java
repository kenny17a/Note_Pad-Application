package com.notepaddiary.notepad.services;


import com.notepaddiary.notepad.data.dtos.request.EntriesRequest;
import com.notepaddiary.notepad.data.dtos.request.RetrieveRequest;
import com.notepaddiary.notepad.data.dtos.response.EntriesRetrieveResponse;
import com.notepaddiary.notepad.data.dtos.response.Response;
import com.notepaddiary.notepad.data.dtos.response.WriteResponse;
import com.notepaddiary.notepad.data.models.Entries;
import  com.notepaddiary.notepad.data.dtos.response.Response.*;

import java.util.List;

public interface EntriesService {
        WriteResponse writeInEntries(EntriesRequest writeRequest);
        EntriesRetrieveResponse retrieveEntries(RetrieveRequest retrieveRequest);
        Response updateEntries(EntriesRequest updateEntriesRequest);
        Response deleteEntries(int id);
        List<Entries> getAllEntries();
        Response deleteAllEntries();
}
