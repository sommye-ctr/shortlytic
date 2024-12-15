package com.mtj.shortlytic.exception;

import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public class UrlExpiredException extends RuntimeException {
    private final String shortCode;
    private final OffsetDateTime expiredAt;

    public UrlExpiredException(String shortCode, OffsetDateTime expiredAt) {
        super("Url expired: " + shortCode + " at " + expiredAt);
        this.shortCode = shortCode;
        this.expiredAt = expiredAt;
    }
}
