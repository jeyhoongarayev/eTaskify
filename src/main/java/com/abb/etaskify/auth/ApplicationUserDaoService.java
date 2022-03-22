package com.abb.etaskify.auth;

import com.abb.etaskify.db.entity.User;
import com.abb.etaskify.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Repository("mysqlDB")
public class ApplicationUserDaoService implements ApplicationUserDao {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return createUser(user);
    }

    private Optional<ApplicationUser> createUser(Optional<User> user) {
        Set<SimpleGrantedAuthority> role = new HashSet<>();
        role.add(new SimpleGrantedAuthority("ROLE_" + user.get().getRole()));
        return Optional.of(new ApplicationUser(
                        user.get().getEmail(),
                        passwordEncoder.encode(user.get().getPassword()),
                        role,
                        true,
                        true,
                        true,
                        true
                )
        );
    }
}
