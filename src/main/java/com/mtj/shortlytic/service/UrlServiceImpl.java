package com.mtj.shortlytic.service;

import application.Constants;
import com.mtj.shortlytic.exception.ResourceNotFoundException;
import com.mtj.shortlytic.models.Url;
import com.mtj.shortlytic.payload.UrlRequest;
import com.mtj.shortlytic.payload.UrlResponse;
import com.mtj.shortlytic.repositories.UrlRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.OffsetDateTime;

@AllArgsConstructor
public class UrlServiceImpl implements UrlService {

    private UrlRepository urlRepository;
    private ModelMapper modelMapper;

    @Override
    public UrlResponse getUrlByShort(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ResourceNotFoundException("Url", shortCode));

        return modelMapper.map(url, UrlResponse.class);
    }

    @Override
    public UrlResponse shortenUrl(UrlRequest urlRequest) {
        Url url = new Url();
        url.setUrl(urlRequest.getUrl());

        url.setPasswordProtected(urlRequest.isPasswordProtected());
        if (urlRequest.isPasswordProtected()) {
            url.setPassword(urlRequest.getPassword()); //todo encode this password
        }

        if (urlRequest.getExpiresIn() != 0) {
            OffsetDateTime dateTime = OffsetDateTime.now().plusHours(urlRequest.getExpiresIn());
            url.setExpiryAt(dateTime);
        }
        url.setShortCode(urlRepository.generateUniqueShortCode(Constants.SHORT_CODE_LENGTH));

        url = urlRepository.save(url);

        //todo set user
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
}
