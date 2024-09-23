package com.mllo.p2evik.service.impl;

import com.mllo.p2evik.entity.User;
import com.mllo.p2evik.repository.UserRepository;
import com.mllo.p2evik.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(long id, User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUser(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findUser(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.findByEmail(username).isPresent();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * Synchronizes the user details with the given keycloak ID.
     *
     * @param keycloakId the keycloak ID of the user
     * @param email the email of the user
     * @param name the name of the user
     */
    public void syncUser(String keycloakId, String email, String name) {
        Optional<User> existingUser = userRepository.findByKeycloakId(keycloakId);

        if (existingUser.isEmpty()) {
            User newUser = new User();
            newUser.setKeycloakId(keycloakId);
            newUser.setEmail(email);
            newUser.setName(name);
            save(newUser);
        } else {
            User user = existingUser.get();
            if (!user.getEmail().equals(email) || !user.getName().equals(name)) {
                user.setEmail(email);
                user.setName(name);
                save(user);
            }
        }
    }

}
