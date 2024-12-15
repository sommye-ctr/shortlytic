package com.mtj.shortlytic.service;

import com.mtj.shortlytic.payload.UrlRequest;
import com.mtj.shortlytic.payload.UrlResponse;

public interface UrlService {
    UrlResponse getUrlByShort(String shortCode, String password);

    UrlResponse shortenUrl(UrlRequest urlRequest);

    UrlResponse updateUrl(String shortCode, UrlRequest urlRequest);

    void deleteUrl(String shortCode);
}
