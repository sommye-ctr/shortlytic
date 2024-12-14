package com.mtj.shortlytic.service;

import com.mtj.shortlytic.payload.DeviceTypeResponse;
import com.mtj.shortlytic.payload.TopCountryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnalyticsService {

    long getTotalCount(long urlId);

    List<TopCountryResponse> getTopCountries(Long urlId, int limit);

    List<DeviceTypeResponse> getDeviceCounts(long urlId);
}
