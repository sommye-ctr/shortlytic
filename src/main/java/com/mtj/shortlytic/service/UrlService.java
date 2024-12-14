package com.mtj.shortlytic.service;

import com.mtj.shortlytic.payload.UrlDTO;
import org.springframework.stereotype.Service;

@Service
public interface UrlService {
    UrlDTO getUrlByShort(String shortCode);
}
