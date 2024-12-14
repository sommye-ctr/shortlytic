package com.mtj.shortlytic.repositories;

import com.mtj.shortlytic.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
