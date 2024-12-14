package com.mtj.shortlytic.payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UrlResponse {
    private Integer id;

    // public access ones
    private String url;
    private String shortCode;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
