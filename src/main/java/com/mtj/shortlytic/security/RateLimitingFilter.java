package com.mtj.shortlytic.security;

import com.mtj.shortlytic.application.Constants;
import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private final Map<String, Bucket> clientBuckets = new ConcurrentHashMap<>();

    private Bucket createBucket(String id) {
        return Bucket.builder()
                .addLimit(limit -> limit.capacity(Constants.RATE_LIMIT_PER_MINUTE).refillIntervally(Constants.RATE_LIMIT_PER_MINUTE, Duration.ofMinutes(1)))
                .build();
    }

    private boolean tryConsume(String clientId) {
        Bucket bucket = clientBuckets.computeIfAbsent(clientId, this::createBucket);
        return bucket.tryConsume(1);
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String ip = request.getRemoteAddr();

        if (tryConsume(ip)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().println("Rate limit exceeded");
        }
    }
}
