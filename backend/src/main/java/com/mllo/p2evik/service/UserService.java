package com.mllo.p2evik.service;

import com.mllo.p2evik.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing users.
 */
public interface UserService {

    /**
     * Saves a new user.
     *
     * @param user the user to be saved
     * @return the saved User object
     */
    User saveUser(User user);

    /**
     * Updates the user with the given ID.
     *
     * @param id   the ID of the user to be updated
     * @param user the new user data
     * @return the updated User object
     */
    User updateUser(long id, User user);

    /**
     * Deletes the user with the given ID.
     *
     * @param id the ID of the user to be deleted
     */
    void deleteUser(long id);

    /**
     * Deletes the user by object.
     *
     * @param user the User object to be deleted
     */
    void deleteUser(User user);

    /**
     * Lists all users.
     *
     * @return a list of all User objects
     */
    List<User> getUsers();

    /**
     * Finds the user with the given ID.
     *
     * @param id the ID of the user to be found
     * @return the User object with the given ID, or null if not found
     */
    User findUser(long id);

    /**
     * Finds the user with the given email.
     *
     * @param email the email of the user to be found
     * @return the User object with the given email, or null if not found
     */
    User findUserByEmail(String email);

    /**
     * Finds the user with the given name.
     *
     * @param name the name of the user to be found
     * @return the User object with the given name, or null if not found
     */
    User findUser(String name);

    /**
     * Checks if a user with the given username exists.
     *
     * @param username the username to check
     * @return true if a user with the given username exists, false otherwise
     */
    boolean existsByUsername(String username);

    /**
     * Checks if a user with the given email exists.
     *
     * @param email the email to check
     * @return true if a user with the given email exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Saves a user.
     *
     * @param user the user to be saved
     */
    void save(User user);

    /**
     * Synchronizes the user details with the given keycloak ID.
     *
     * @param keycloakId the keycloak ID of the user
     * @param email      the email of the user
     * @param name       the name of the user
     */
    void syncUser(String keycloakId, String email, String name);
}
