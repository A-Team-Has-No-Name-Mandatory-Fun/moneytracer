package com.app.moneytracer.service;

import com.app.moneytracer.configuration.ApplicationUserRole;
import com.app.moneytracer.configuration.PasswordConfig;
import com.app.moneytracer.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordConfig passwordConfig;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        try
        {
            com.app.moneytracer.model.User user = userRepository.findByUsername(username);
            List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                    new SimpleGrantedAuthority(ApplicationUserRole.USER.name()));

            return new User(user.getUsername(), passwordConfig.passwordEncoder().encode(user.getPassword()), authorities);
        }
        catch (UsernameNotFoundException e)
        {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
