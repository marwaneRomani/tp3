package com.marwane.tp3.security;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class SecurityConfigJdbc {

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource, PasswordEncoder passwordEncoder){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.createUser(
            User.withUsername("user1")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .build()
        );
        jdbcUserDetailsManager.createUser(
            User.withUsername("user2")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .build()
        );
        jdbcUserDetailsManager.createUser(
            User.withUsername("admin")
                .password(passwordEncoder.encode("1234"))
                .roles("USER", "ADMIN")
                .build()
        );
        return jdbcUserDetailsManager;
    }
}
