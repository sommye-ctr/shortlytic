package com.mtj.shortlytic.repositories;

import com.mtj.shortlytic.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.UUID;

@RequestMapping
public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortCode(String shortCode);

    @Procedure("generate_unique_short_code")
    String generateUniqueShortCode(int length);

    Optional<Url> findByShortCodeAndUserId(String shortCode, UUID userId);
}
