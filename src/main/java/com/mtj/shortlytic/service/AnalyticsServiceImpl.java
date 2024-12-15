package com.mtj.shortlytic.service;

import com.mtj.shortlytic.payload.DeviceTypeResponse;
import com.mtj.shortlytic.payload.TopCountryResponse;
import com.mtj.shortlytic.repositories.AnalyticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    private AnalyticsRepository analyticsRepository;

    @PreAuthorize("@authUtils.canAccessAnalytics(#urlId)")
    @Override
    public long getTotalCount(long urlId) {
        return analyticsRepository.countByUrlId(urlId);
    }

    @PreAuthorize("@authUtils.canAccessAnalytics(#urlId)")
    @Override
    public List<TopCountryResponse> getTopCountries(Long urlId, int limit) {
        return analyticsRepository.getTopCountriesWithClickCount(urlId, limit);
    }

    @PreAuthorize("@authUtils.canAccessAnalytics(#urlId)")
    @Override
    public List<DeviceTypeResponse> getDeviceCounts(long urlId) {
        return analyticsRepository.getDeviceTypesWithClickCount(urlId);
    }
}
