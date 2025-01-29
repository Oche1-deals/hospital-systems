/**
 * Created By: Innocent Idoko
 * Time:15:24
 */
package com.Hospital_mang.system.Service.impl;

import com.Hospital_mang.system.Service.UserService;
import com.Hospital_mang.system.model.Login;
import com.Hospital_mang.system.model.Role;
import com.Hospital_mang.system.repository.LoginRepository;
import com.Hospital_mang.system.repository.RoleRepository;
import com.Hospital_mang.system.request.UserRequest;
import com.Hospital_mang.system.response.MessageResponseObject;
import com.Hospital_mang.system.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserUtil userUtil;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final LoginRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public Login getUserByUsername(String username) {
        return userUtil.getUserByUsername(username);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
    public Boolean checkMatchPassword(String password,String comparePassword) {
        return passwordEncoder.matches(password,comparePassword);
    }

    public ResponseEntity<?> create(UserRequest request) {
        Login newUser;
        List<Object> responseList = null;
        try {

            responseList = new ArrayList<>();
//Role role = roleRepository.findById(userRole).orElse(null);
            newUser = new Login();
            newUser.setStaffId(request.getId());
            newUser.setEmail(request.getFirstName());
            newUser.setPassword(getEncodedPassword(request.getPassword()));
            Role roles = null;
           // roles.setRoleId(request.getRole());
            newUser.setRoles(request.getRoles());
            newUser = userRepository.save(newUser);
            responseList.add(newUser);

        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseObject("Error: Username or email is already taken!", HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.ok(new MessageResponseObject("User registered successfully!", HttpStatus.CREATED.value(), responseList));
    }
}
