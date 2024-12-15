package com.mtj.shortlytic.service;

import com.mtj.shortlytic.application.Constants;
import com.mtj.shortlytic.exception.ResourceNotFoundException;
import com.mtj.shortlytic.exception.UrlExpiredException;
import com.mtj.shortlytic.models.Url;
import com.mtj.shortlytic.payload.UrlRequest;
import com.mtj.shortlytic.payload.UrlResponse;
import com.mtj.shortlytic.repositories.UrlRepository;
import com.mtj.shortlytic.utils.AuthUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final PasswordEncoder passwordEncoder;
    private UrlRepository urlRepository;
    private ModelMapper modelMapper;

    @Override
    public UrlResponse getUrlByShort(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ResourceNotFoundException("Url", shortCode));

        if (url.getExpiryAt().isBefore(OffsetDateTime.now())) {
            throw new UrlExpiredException(shortCode, url.getExpiryAt());
        }

        return modelMapper.map(url, UrlResponse.class);
    }

    @Override
    public UrlResponse shortenUrl(UrlRequest urlRequest) {
        Url url = new Url();
        url.setUrl(urlRequest.getUrl());

        url.setPasswordProtected(urlRequest.isPasswordProtected());
        if (urlRequest.isPasswordProtected()) {
            url.setPassword(passwordEncoder.encode(urlRequest.getPassword()));
        }

        if (urlRequest.getExpiresIn() != 0) {
            OffsetDateTime dateTime = OffsetDateTime.now().plusHours(urlRequest.getExpiresIn());
            url.setExpiryAt(dateTime);
        }
        url.setShortCode(urlRepository.generateUniqueShortCode(Constants.SHORT_CODE_LENGTH));
        url.setUser(AuthUtils.getCurrentUser());
        url = urlRepository.save(url);

        return modelMapper.map(url, UrlResponse.class);
    }

    @Override
    public UrlResponse updateUrl(String shortCode, UrlRequest urlRequest) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ResourceNotFoundException("Url", shortCode));

        url.update(urlRequest);
        url = urlRepository.save(url);
        return modelMapper.map(url, UrlResponse.class);
    }

    @Override
    public void deleteUrl(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ResourceNotFoundException("Url", shortCode));

        urlRepository.delete(url);
    }
}
