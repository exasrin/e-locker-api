package com.example.lock_and_load.service.impl;

import com.example.lock_and_load.dto.request.NewLockerRequest;
import com.example.lock_and_load.dto.request.UpdateLockerRequest;
import com.example.lock_and_load.dto.response.LockerResponse;
import com.example.lock_and_load.entity.Locker;
import com.example.lock_and_load.entity.User;
import com.example.lock_and_load.repository.LockerRepository;
import com.example.lock_and_load.service.LockerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LockerServiceImpl implements LockerService {
    private final LockerRepository lockerRepository;
    @Override
    public LockerResponse createNew(NewLockerRequest request) {
        User user = User.builder()
                .id(request.getUserId())
                .build();
        Locker locker = Locker.builder()
                .id(UUID.randomUUID().toString())
                .capacity(request.getCapacity())
                .status(request.getStatus())
                .user(user)
                .build();
        lockerRepository.saveAndFlush(locker);
        return mapToResponse(locker);
    }

    @Override
    public LockerResponse getById(String id) {
        Locker locker = findByIdOrThrowNotFound(id);
        return mapToResponse(locker);
    }

    @Override
    public List<LockerResponse> getAll() {
        return lockerRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public LockerResponse update(UpdateLockerRequest request) {
        User user = User.builder()
                .id(request.getUserId())
                .build();
        Locker locker = findByIdOrThrowNotFound(request.getId());
        locker.setCapacity(request.getCapacity());
        locker.setStatus(request.getStatus());
        locker.setUser(user);
        lockerRepository.update(locker);
        return mapToResponse(locker);
    }

    @Override
    public void deleteById(String id) {
        lockerRepository.deleteById(id);
    }

    private Locker findByIdOrThrowNotFound(String id) {
        Optional<Locker> locker = Optional.ofNullable(lockerRepository.findById(id));
        return locker.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "locker not found"));
    }

    private LockerResponse mapToResponse(Locker response) {
        return LockerResponse.builder()
                .lockerId(response.getId())
                .capacity(response.getCapacity())
                .status(response.getStatus())
                .userId(response.getUser().getId())
                .build();
    }
}
