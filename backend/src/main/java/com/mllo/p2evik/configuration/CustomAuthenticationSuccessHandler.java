package com.mllo.p2evik.configuration;

import com.mllo.p2evik.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws ServletException, IOException {

        Jwt jwt = (Jwt) authentication.getPrincipal();

        syncUserDetails(jwt);
        super.onAuthenticationSuccess(request, response, authentication);
    }

    private void syncUserDetails(Jwt jwt) {
        String keycloakId = jwt.getSubject();
        String email = jwt.getClaim("email");
        String name = jwt.getClaim("name");

        userService.syncUser(keycloakId, email, name);
    }
}