package com.mllo.p2evik.configuration;

import com.mllo.p2evik.entity.User;
import com.mllo.p2evik.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class LoadDatabase {

    @Bean
    public CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            log.info("Preloading {}", repository.save(new User("User 1")));
            log.info("Preloading {}", repository.save(new User("User 2")));
        };
    }
}
