package com.mtj.shortlytic.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "urls")
public class Url {
    @Id
    private Integer id;

    @NotNull @NotEmpty
    private String url;

    @NotNull @NotEmpty
    private String shortCode;

    private boolean passwordProtected = false;
    private String password;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime expiryAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "url")
    private List<Analytics> analytics;
}
