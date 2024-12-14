package com.mtj.shortlytic.controller;

import com.mtj.shortlytic.payload.UrlDTO;
import com.mtj.shortlytic.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private UrlService urlService;

    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlDTO> getUrl(@PathVariable String shortCode) {
        UrlDTO urlDTO = urlService.getUrlByShort(shortCode);
        return ResponseEntity.ok(urlDTO);
    }
}
