package com.mtj.shortlytic.payload;

import lombok.*;

import java.time.OffsetDateTime;


@Setter
@Getter
public class UrlRequest {
    private String url;
    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;
    private String password;
    private boolean passwordProtected;
    private int expiresIn; // in hours
}
