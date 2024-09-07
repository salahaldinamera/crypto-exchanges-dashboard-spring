package com.crypto.dashboardweb.dao;

import com.crypto.dashboardweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Get user as user optional by email from database
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);
}
