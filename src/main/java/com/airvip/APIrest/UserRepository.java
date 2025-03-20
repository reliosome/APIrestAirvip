package com.airvip.APIrest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This interface allows access to the database for User entities
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // Custom query method
}
