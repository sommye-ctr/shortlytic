package com.mtj.shortlytic.utils;

import com.mtj.shortlytic.models.Url;
import com.mtj.shortlytic.models.User;
import com.mtj.shortlytic.repositories.UrlRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AuthUtils {

    private final UrlRepository urlRepository;

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return (User) authentication.getPrincipal();
        }
        return null; // No authenticated user
    }

    public boolean canAccessUrl(String shortCode) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return false;
        }

        Optional<Url> o = urlRepository.findByShortCodeAndUserId(shortCode, currentUser.getId());
        return o.isPresent();
    }

    public boolean canAccessAnalytics(long urlId) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return false;
        }

        Optional<Url> o = urlRepository.findById(urlId);
        return o.isPresent();
    }
}
