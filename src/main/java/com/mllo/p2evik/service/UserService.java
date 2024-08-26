package com.mllo.p2evik.service;

import com.mllo.p2evik.entity.User;

import java.util.List;

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
     * @param id the ID of the user to be updated
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
     * Finds the user with the given name.
     *
     * @param name the name of the user to be found
     * @return the User object with the given name, or null if not found
     */
    User findUser(String name);
}
