package com.carregisterapi.users.service;
import com.carregisterapi.users.model.User;
import com.carregisterapi.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserServiceInterface, UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = Optional.ofNullable(this.userRepository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("Usuario inexistente ou senha invalida"));
        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList(
                "ROLE_USER", "ROLE_ADMIN");
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList(
                "ROLE_USER");

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.getIsAdmin() ? authorityListAdmin
                : authorityListUser);
    }

        public User getLoggedUser() {
            Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String email;

            if (loggedUser instanceof UserDetails) {
                email = ((UserDetails) loggedUser).getUsername();
            } else {
                 email = loggedUser.toString();
            }

            return userRepository.findByEmail(email);
        }


    @Override
    public String passwordEncript(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
