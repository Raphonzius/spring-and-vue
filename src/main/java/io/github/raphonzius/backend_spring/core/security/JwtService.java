package io.github.raphonzius.backend_spring.core.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

import static io.github.raphonzius.backend_spring.core.utils.ConstantUtils.CLAIM_NAME;
import static io.github.raphonzius.backend_spring.core.utils.ConstantUtils.JOB_ID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtService {
    private final JwtEncoder encoder;

    public String generateToken(Authentication auth) {
        Instant now = Instant.now();
        long expiry = 300L;

        String scopes = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer(JOB_ID)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(auth.getName())
                .claim(CLAIM_NAME, scopes)
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
