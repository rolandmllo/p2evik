package com.mllo.p2evik.repository;

import com.mllo.p2evik.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Repository interface for managing Role entities.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds roles by their name.
     *
     * @param name the name of the roles to find
     * @return a set of Role objects with the given name
     */
    Set<Role> findByName (String name);
}