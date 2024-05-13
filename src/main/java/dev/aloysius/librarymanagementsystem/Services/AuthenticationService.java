package dev.aloysius.librarymanagementsystem.Services;

import dev.aloysius.librarymanagementsystem.Domains.AuthenticationRequest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {
    private final JwtEncoder jwtEncoder;
    private final AuthenticationProvider authenticationProvider;

    public AuthenticationService(JwtEncoder jwtEncoder, AuthenticationProvider authenticationProvider) {
        this.jwtEncoder = jwtEncoder;
        this.authenticationProvider = authenticationProvider;
    }

    public String authenticate(AuthenticationRequest request) {
        Authentication authenticate = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authenticate);
        return generateToken(authenticate);
    }

    private String generateToken(Authentication authentication){
        String claim = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .subject(authentication.getName())
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .claim("claim", claim)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();

    }
}
