package com.mllo.p2evik.repository;

import com.mllo.p2evik.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findByName (String name);
}