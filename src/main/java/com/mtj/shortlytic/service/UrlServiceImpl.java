package com.mtj.shortlytic.service;

import com.mtj.shortlytic.exception.ResourceNotFoundException;
import com.mtj.shortlytic.models.Url;
import com.mtj.shortlytic.payload.UrlDTO;
import com.mtj.shortlytic.repositories.UrlRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class UrlServiceImpl implements UrlService {

    private UrlRepository urlRepository;
    private ModelMapper modelMapper;

    @Override
    public UrlDTO getUrlByShort(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ResourceNotFoundException("Url", shortCode));

        return modelMapper.map(url, UrlDTO.class);
    }
}
