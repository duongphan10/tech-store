package com.example.techstore;

import com.example.techstore.constant.RoleConstant;
import com.example.techstore.domain.entity.Role;
import com.example.techstore.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class TechStoreApplication {
    private final RoleRepository roleRepository;

    public static void main(String[] args) {
        Environment env = SpringApplication.run(TechStoreApplication.class, args).getEnvironment();
        String appName = env.getProperty("spring.application.name");
        if (appName != null) {
            appName = appName.toUpperCase();
        }
        String port = env.getProperty("server.port");
        log.info(" Url swagger-ui: http://localhost:" + port + "/swagger-ui.html");
        log.info(" ----- START SUCCESS " + appName + " Application -----");
    }

    @Bean
    CommandLineRunner init() {
        return args -> {
            //init role
            if (roleRepository.count() == 0) {
                roleRepository.save(new Role(null, RoleConstant.USER, null, null));
                roleRepository.save(new Role(null, RoleConstant.ADMIN, null, null));
                roleRepository.save(new Role(null, RoleConstant.SUPPORT, null, null));
            }
        };
    }

}
