package com.example.lock_and_load.dto.request;

import com.example.lock_and_load.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewLockerRequest {
    private Integer capacity;
    private String status;
    private String userId;
}
