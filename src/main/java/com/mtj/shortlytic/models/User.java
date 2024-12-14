package com.mtj.shortlytic.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private UUID id;

    @NotEmpty @NotNull
    @Size(max = 255, message = "Username length cannot exceed 255!")
    private String username;

    @NotNull @NotEmpty
    @Size(max = 255, message = "Password length cannot exceed 255!")
    private String password;

    @Email @NotNull @NotEmpty
    @Size(max = 255, message = "Email length cannot exceed 255!")
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Url> urls = new ArrayList<>();

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
