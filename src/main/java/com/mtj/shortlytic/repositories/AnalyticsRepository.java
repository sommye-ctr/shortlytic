package com.mtj.shortlytic.repositories;

import com.mtj.shortlytic.models.Analytics;
import com.mtj.shortlytic.payload.DeviceTypeResponse;
import com.mtj.shortlytic.payload.TopCountryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnalyticsRepository extends JpaRepository<Analytics, Long> {
    @Query("SELECT a.country, COUNT(*) FROM Analytics a WHERE  a.url = ?1 GROUP BY a.country ORDER BY COUNT(*) DESC LIMIT ?2")
    List<TopCountryResponse> getTopCountriesWithClickCount(Long urlId, int limit);

    @Query("SELECT COUNT(*) FROM Analytics a WHERE a.url = ?1")
    Long countByUrlId(Long urlId);

    @Query("SELECT a.deviceType, COUNT(*) FROM Analytics a WHERE a.url = ?1 GROUP BY a.deviceType ORDER BY COUNT(*) DESC")
    List<DeviceTypeResponse> getDeviceTypesWithClickCount(Long urlId);
}
