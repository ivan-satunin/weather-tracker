package com.me.weathertracker.service;

import com.me.weathertracker.config.AppUserDetails;
import com.me.weathertracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Does not exists user: " + username));
        return new AppUserDetails(user);
    }
}
