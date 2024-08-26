package com.mllo.p2evik.repository;

import com.mllo.p2evik.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);
}
