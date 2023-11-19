package com.example.lock_and_load.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LockerResponse {
    private String lockerId;
    private Integer capacity;
    private String status;
    private String userId;
}
