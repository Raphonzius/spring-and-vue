package io.github.raphonzius.backend_spring.core.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationService {

    private final JwtService jwtService;

    public String authenticate(Authentication auth) {
        return jwtService.generateToken(auth);
    }

}
