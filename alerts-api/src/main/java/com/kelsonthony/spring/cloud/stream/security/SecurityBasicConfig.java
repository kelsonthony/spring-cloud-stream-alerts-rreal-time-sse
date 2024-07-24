package com.kelsonthony.spring.cloud.stream.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.kelsonthony.spring.cloud.stream.domain.user.User;
import com.kelsonthony.spring.cloud.stream.domain.user.UserRepository;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityBasicConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers( "/user/**").authenticated()
            .and()
            .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(UserRepository userRepository) {
        User[] users = userRepository.findAll().toArray(new User[]{});
        return new InMemoryUserDetailsManager(users);
    }
}