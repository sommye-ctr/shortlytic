package com.mtj.shortlytic.controller;

import com.mtj.shortlytic.payload.UrlRequest;
import com.mtj.shortlytic.payload.UrlResponse;
import com.mtj.shortlytic.service.UrlService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private UrlService urlService;

    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlResponse> getUrl(@PathVariable String shortCode) {
        UrlResponse urlResponse = urlService.getUrlByShort(shortCode);
        return ResponseEntity.ok(urlResponse);
    }

    @PostMapping()
    public ResponseEntity<UrlResponse> shortenUrl(@Valid @RequestBody UrlRequest urlRequest) {
        UrlResponse urlResponse = urlService.shortenUrl(urlRequest);
        return ResponseEntity.ok(urlResponse);
    }

    @PutMapping("/{shortCode}")
    public ResponseEntity<UrlResponse> updateUrl(@Valid @RequestBody UrlRequest urlRequest, @PathVariable String shortCode) {
        UrlResponse urlResponse = urlService.updateUrl(shortCode, urlRequest);
        return ResponseEntity.ok(urlResponse);
    }
}
