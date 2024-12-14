package com.mtj.shortlytic.service;

import com.mtj.shortlytic.payload.DeviceTypeResponse;
import com.mtj.shortlytic.payload.TopCountryResponse;
import com.mtj.shortlytic.repositories.AnalyticsRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private AnalyticsRepository analyticsRepository;

    @Override
    public long getTotalCount(long urlId) {
        return analyticsRepository.countByUrlId(urlId);
    }

    @Override
    public List<TopCountryResponse> getTopCountries(Long urlId, int limit) {
        return analyticsRepository.getTopCountriesWithClickCount(urlId, limit);
    }

    @Override
    public List<DeviceTypeResponse> getDeviceCounts(long urlId) {
        return analyticsRepository.getDeviceTypesWithClickCount(urlId);
    }
}
