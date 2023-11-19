package com.example.lock_and_load.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateLockerRequest {
    private String id;
    private Integer capacity;
    private String status;
    private String userId;
}
