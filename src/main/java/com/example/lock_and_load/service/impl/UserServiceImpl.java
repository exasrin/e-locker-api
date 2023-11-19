package com.example.lock_and_load.service.impl;

import com.example.lock_and_load.dto.request.NewUserRequest;
import com.example.lock_and_load.dto.request.UpdateUserRequest;
import com.example.lock_and_load.dto.response.UserResponse;
import com.example.lock_and_load.entity.User;
import com.example.lock_and_load.repository.UserRepository;
import com.example.lock_and_load.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserResponse createNew(NewUserRequest request) {
        try {
            User user = User.builder()
                    .id(UUID.randomUUID().toString())
                    .name(request.getName())
                    .address(request.getAddress())
                    .phoneNumber(request.getPhoneNumber())
                    .password(request.getPassword())
                    .build();
            userRepository.saveAndFlush(user);
            return mapToResponse(user);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "phone number already exist");
        }
    }

    @Override
    public UserResponse getById(String id) {
        User user = findByIdOrThrowNotFound(id);
        return mapToResponse(user);
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public UserResponse update(UpdateUserRequest request) {
        try {
            User user = findByIdOrThrowNotFound(request.getId());
            user.setName(request.getName());
            user.setAddress(request.getAddress());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setPassword(request.getPassword());
            userRepository.update(user);
            return mapToResponse(user);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "phone number already exist");
        }
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    private User findByIdOrThrowNotFound(String id) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id));
        return user.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
    }

    private UserResponse mapToResponse(User response) {
        return UserResponse.builder()
                .id(response.getId())
                .name(response.getName())
                .phoneNumber(response.getPhoneNumber())
                .address(response.getAddress())
                .password(response.getPassword())
                .build();
    }
}
