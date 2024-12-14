package com.mtj.shortlytic.payload;

import lombok.Data;

@Data
public class TopCountryResponse {
    private String country;
    private long count;
}
