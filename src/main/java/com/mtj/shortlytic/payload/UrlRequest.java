package com.mtj.shortlytic.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
public class UrlRequest {
    @NotNull @NotEmpty
    private String url;
    private String password;
    private boolean passwordProtected;
    private int expiresIn; // in hours
}
