package com.example.lock_and_load.service;

import com.example.lock_and_load.dto.request.NewUserRequest;
import com.example.lock_and_load.dto.request.UpdateUserRequest;
import com.example.lock_and_load.dto.response.UserResponse;
import com.example.lock_and_load.entity.User;

import java.util.List;

public interface UserService {
    UserResponse createNew(NewUserRequest request);
    UserResponse getById(String id);
    List<UserResponse> getAll();
    UserResponse update(UpdateUserRequest request);
    void deleteById(String id);
}
