package com.mtj.shortlytic.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserRequest {
    @NotEmpty @NotNull
    @Size(max = 255, message = "Username length cannot exceed 255!")
    private String username;

    @NotNull @NotEmpty
    @Size(max = 255, message = "Password length cannot exceed 255!")
    private String password;

    @Email @NotNull @NotEmpty
    @Size(max = 255, message = "Email length cannot exceed 255!")
    private String email;
}
