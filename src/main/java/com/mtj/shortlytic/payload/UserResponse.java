package com.mtj.shortlytic.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class UserResponse {
    private UUID id;
    private String username;
    private String email;
    private String jwtToken;
}
