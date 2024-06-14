package com.annyarusova.subscriptionservice.security;

import com.annyarusova.subscriptionservice.entity.UserEntity;
import com.annyarusova.subscriptionservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity person = userRepository.findByLoginIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return User.builder()
                .username(person.getLogin())
                .password(person.getPassword())
                .build();
    }
}
