package com.mtj.shortlytic.controller;

import com.mtj.shortlytic.payload.TopCountryResponse;
import com.mtj.shortlytic.service.AnalyticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {
    private AnalyticsService analyticsService;

    @GetMapping("/click-count/{urlId}")
    public ResponseEntity<Long> getTotalCount(@PathVariable long urlId){
       long count =  analyticsService.getTotalCount(urlId);
       return ResponseEntity.ok(count);
    }

    @GetMapping("/top-countries/{urlId}")
    public ResponseEntity<List<TopCountryResponse>> getTopCountries(@PathVariable Long urlId,
                                                                    @RequestParam(required = false, defaultValue = "5") int limit) {
        List<TopCountryResponse> list = analyticsService.getTopCountries(urlId, limit);
        return ResponseEntity.ok(list);
    }
}
