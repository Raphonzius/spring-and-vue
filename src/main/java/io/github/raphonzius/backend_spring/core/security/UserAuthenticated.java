package io.github.raphonzius.backend_spring.core.security;

import io.github.raphonzius.backend_spring.core.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Responsavel pelo encapsulamento das informacoes do usuario autenticado
 */

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserAuthenticated implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_USER");
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

}
