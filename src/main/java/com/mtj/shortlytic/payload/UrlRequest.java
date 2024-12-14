package com.mtj.shortlytic.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UrlRequest {
    private String url;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String password;
    private boolean passwordProtected;
    private int expiresIn; // in hours
}
