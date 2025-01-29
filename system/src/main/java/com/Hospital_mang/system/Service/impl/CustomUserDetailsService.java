/**
 * Created By: Innocent Idoko
 * Time:15:18
 */
package com.Hospital_mang.system.Service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.Hospital_mang.system.Service.UserService;
import com.Hospital_mang.system.model.Login;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Data
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserService userService;
    private String email;
    private String phoneNumber;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //System.out.println("user CustomUserDetailsService");
        Login user = userService.getUserByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                user.getStaffId(),
                user.getPassword(),
                getAuthority(user)
        );

    }

    private Set<GrantedAuthority> getAuthority(Login user) {

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getDescription()))
                .collect(Collectors.toSet());

        return authorities;
    }

}

