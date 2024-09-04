package com.mllo.p2evik.repository;

import com.mllo.p2evik.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing User entities.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their name.
     *
     * @param name the name of the user to find
     * @return the User object with the given name, or null if not found
     */
    User findByName(String name);

    /**
     * Finds a user by their email.
     *
     * @param email the email of the user to find
     * @return the User object with the given email, or null if not found
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user with the given email exists.
     *
     * @param email the email to check
     * @return true if a user with the given email exists, false otherwise
     */
    Boolean existsByEmail(String email);
}
