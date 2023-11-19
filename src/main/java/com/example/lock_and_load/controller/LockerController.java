package com.example.lock_and_load.controller;

import com.example.lock_and_load.dto.request.NewLockerRequest;
import com.example.lock_and_load.dto.request.UpdateLockerRequest;
import com.example.lock_and_load.dto.response.CommonResponse;
import com.example.lock_and_load.dto.response.LockerResponse;
import com.example.lock_and_load.service.LockerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.locks.Lock;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lockers")
public class LockerController {
    private final LockerService lockerService;

    @PostMapping
    public ResponseEntity<?> createLocker(@RequestBody NewLockerRequest request) {
        LockerResponse lockerResponse = lockerService.createNew(request);
        CommonResponse<LockerResponse> response = CommonResponse.<LockerResponse>builder()
                .message("successfully create new locker")
                .statusCode(HttpStatus.CREATED.value())
                .data(lockerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLockerById(@PathVariable String id) {
        LockerResponse lockerResponse = lockerService.getById(id);
        CommonResponse<LockerResponse> response = CommonResponse.<LockerResponse>builder()
                .message("successfully get locker by id")
                .statusCode(HttpStatus.OK.value())
                .data(lockerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllLocker() {
        List<LockerResponse> lockerResponses = lockerService.getAll();
        CommonResponse<List<LockerResponse>> response = CommonResponse.<List<LockerResponse>>builder()
                .message("successfully get locker")
                .statusCode(HttpStatus.OK.value())
                .data(lockerResponses)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateLocker(@RequestBody UpdateLockerRequest request) {
        LockerResponse lockerResponse = lockerService.update(request);
        CommonResponse<LockerResponse> response = CommonResponse.<LockerResponse>builder()
                .message("successfully update locker")
                .statusCode(HttpStatus.OK.value())
                .data(lockerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLockerById(@PathVariable String id) {
        lockerService.deleteById(id);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .message("successfully delete locker")
                .statusCode(HttpStatus.OK.value())
                .data("OK")
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
