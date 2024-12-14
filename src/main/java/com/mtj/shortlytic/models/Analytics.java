package com.mtj.shortlytic.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.InetAddress;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "analytics")
public class Analytics {
    @Id
    private Long id;

    private OffsetDateTime clickedAt;
    private String deviceType;
    private InetAddress ipAddress;
    private String country;

    @ManyToOne
    @JoinColumn(name = "url_id")
    private Url url;
}
