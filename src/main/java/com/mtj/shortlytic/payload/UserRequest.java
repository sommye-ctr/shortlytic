package com.mtj.shortlytic.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserRequest {
    private String username;
    private String password;
    private String email;
}
