package com.erensayar.cocauthserver.component;

import com.erensayar.cocauthserver.model.entity.User;
import com.erensayar.cocauthserver.model.enums.Role;
import com.erensayar.cocauthserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SuppressWarnings("PMD")
@Configuration
public class CmdLineRunner {

    @Bean
    public CommandLineRunner loadData(@Autowired final UserRepository userRepository,
                                      @Autowired final PasswordEncoder passwordEncoder) {
        return (args) -> {
            System.out.println("-------------------");
            System.out.println("### APP STARTED ###");

            User user1 = userRepository.save(User.builder()
                    .email("user1@mail.com")
                    .roles(Collections.singleton(Role.USER))
                    .username("User_1")
                    .password(passwordEncoder.encode("User_1_pass"))
                    .mailVerification(true)
                    .build());

            System.out.println(user1.toString());

        };
    }

}
