package com.mtj.shortlytic.service;

import com.mtj.shortlytic.payload.DeviceTypeResponse;
import com.mtj.shortlytic.payload.TopCountryResponse;

import java.util.List;

public interface AnalyticsService {

    long getTotalCount(long urlId);

    List<TopCountryResponse> getTopCountries(Long urlId, int limit);

    List<DeviceTypeResponse> getDeviceCounts(long urlId);
}
