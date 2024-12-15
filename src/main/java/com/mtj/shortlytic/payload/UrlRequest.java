package com.mtj.shortlytic.payload;

import lombok.*;

@Setter
@Getter
public class UrlRequest {
    private String url;
    private String password;
    private boolean passwordProtected;
    private int expiresIn; // in hours
}
