package com.mtj.shortlytic.service;

import com.mtj.shortlytic.payload.TopCountryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnalyticsService {

    long getTotalCount(String shortCode);

    List<TopCountryResponse> getTopCountries(Long urlId, int limit);
}
