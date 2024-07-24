package com.kelsonthony.spring.cloud.stream.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kelsonthony.spring.cloud.stream.domain.user.User;
import com.kelsonthony.spring.cloud.stream.domain.user.UserRepository;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepository userRepository;

    public User getAuthenticatedUser() {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow();
    }

}
