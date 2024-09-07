package com.crypto.dashboardweb.dao;

import com.crypto.dashboardweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Get user as user optional by username from database
     * @param username
     * @return
     */
    Optional<User> findByUsername(String username);
}
