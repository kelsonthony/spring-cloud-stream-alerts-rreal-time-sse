package com.kelsonthony.spring.cloud.stream.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.kelsonthony.spring.cloud.stream.domain.alert.AlertService;
import com.kelsonthony.spring.cloud.stream.domain.user.User;
import com.kelsonthony.spring.cloud.stream.security.SecurityService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserAlertsController {

    private final SecurityService securityService;
    private final AlertService alertService;

    @GetMapping(value = "/user/me")
    public User getUser() {
        return securityService.getAuthenticatedUser();
    }

    @GetMapping("/user/me/alerts")
    public SseEmitter getAlerts() throws IOException {
        return alertService.watchAlerts();
    }
}
