package com.example.lock_and_load.service;

import com.example.lock_and_load.dto.request.NewLockerRequest;
import com.example.lock_and_load.dto.request.UpdateLockerRequest;
import com.example.lock_and_load.dto.response.LockerResponse;
import com.example.lock_and_load.entity.Locker;

import java.util.List;

public interface LockerService {
    LockerResponse createNew(NewLockerRequest request);
    LockerResponse getById(String id);
    List<LockerResponse> getAll();
    LockerResponse update(UpdateLockerRequest request);
    void deleteById(String id);
}
