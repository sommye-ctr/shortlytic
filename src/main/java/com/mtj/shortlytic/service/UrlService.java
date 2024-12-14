package com.mtj.shortlytic.service;

import com.mtj.shortlytic.payload.UrlRequest;
import com.mtj.shortlytic.payload.UrlResponse;
import org.springframework.stereotype.Service;

@Service
public interface UrlService {
    UrlResponse getUrlByShort(String shortCode);

    UrlResponse shortenUrl(UrlRequest urlRequest);

    UrlResponse updateUrl(String shortCode, UrlRequest urlRequest);
}
