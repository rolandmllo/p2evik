package com.mllo.p2evik.configuration;

import com.mllo.p2evik.entity.Role;
import com.mllo.p2evik.entity.User;
import com.mllo.p2evik.entity.types.UserRoleType;
import com.mllo.p2evik.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Log4j2
@Configuration
public class LoadInitialData {

    @Bean
    public CommandLineRunner initDatabase(UserRepository repository) {

        return args -> {
            var user1 = new User();
            user1.setEmail("user1@example.com");
            user1.setRoles(Set.of(new Role(UserRoleType.TEACHER)));

            log.info("Preloading {}", repository.save(user1));
        };
    }
}
