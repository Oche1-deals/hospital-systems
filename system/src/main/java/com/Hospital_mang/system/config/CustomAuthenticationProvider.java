/**
 * Created By: Innocent Idoko
 * Time:15:11
 */
package com.Hospital_mang.system.config;

import com.Hospital_mang.system.Service.UserService;
import com.Hospital_mang.system.model.Login;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Data
public class CustomAuthenticationProvider implements UserDetailsService {

    @Autowired
    private final UserService userService;
    private String email;
    private String phoneNumber;



    private Set<SimpleGrantedAuthority> getAuthority(Login user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        user.set().forEach(role -> {
//
//        });
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoleId().getDescription()));
        authorities.add(new SimpleGrantedAuthority("ROLEID_" + user.getRoleId().getRoleId()));
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login user = userService.getUserByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                user.getStaffId(),
                user.getPassword(),
                getAuthority(user)
        );
    }
}
