package com.mtj.shortlytic.models;

import com.mtj.shortlytic.payload.UrlRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull @NotEmpty
    private String url;

    @NotNull @NotEmpty
    private String shortCode;

    private boolean passwordProtected = false;
    private String password;

    @CreationTimestamp
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
    private OffsetDateTime expiryAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "url")
    private List<Analytics> analytics;

    public void update(UrlRequest urlRequest){
        url = urlRequest.getUrl() == null ? url : urlRequest.getUrl();
        if (passwordProtected != urlRequest.isPasswordProtected()) {
            passwordProtected = urlRequest.isPasswordProtected();
            password = urlRequest.getPassword();
        }
    }
}
