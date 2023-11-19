package com.example.lock_and_load.controller;

import com.example.lock_and_load.dto.request.NewUserRequest;
import com.example.lock_and_load.dto.request.UpdateUserRequest;
import com.example.lock_and_load.dto.response.CommonResponse;
import com.example.lock_and_load.dto.response.UserResponse;
import com.example.lock_and_load.dto.response.UserResponse;
import com.example.lock_and_load.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody NewUserRequest request) {
        UserResponse userResponse = userService.createNew(request);
        CommonResponse<UserResponse> response = CommonResponse.<UserResponse>builder()
                .message("successfully create new user")
                .statusCode(HttpStatus.CREATED.value())
                .data(userResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        UserResponse userResponse = userService.getById(id);
        CommonResponse<UserResponse> response = CommonResponse.<UserResponse>builder()
                .message("successfully get user by id")
                .statusCode(HttpStatus.OK.value())
                .data(userResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllUser() {
        List<UserResponse> userResponses = userService.getAll();
        CommonResponse<List<UserResponse>> response = CommonResponse.<List<UserResponse>>builder()
                .message("successfully get user")
                .statusCode(HttpStatus.OK.value())
                .data(userResponses)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest request) {
        UserResponse userResponse = userService.update(request);
        CommonResponse<UserResponse> response = CommonResponse.<UserResponse>builder()
                .message("successfully update user")
                .statusCode(HttpStatus.OK.value())
                .data(userResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {
        userService.deleteById(id);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .message("successfully delete user")
                .statusCode(HttpStatus.OK.value())
                .data("OK")
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
