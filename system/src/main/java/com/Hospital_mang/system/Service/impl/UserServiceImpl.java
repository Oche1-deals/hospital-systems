/**
 * Created By: Innocent Idoko
 * Time:15:24
 */
package com.Hospital_mang.system.Service.impl;

import com.Hospital_mang.system.Service.*;
import com.Hospital_mang.system.exception.UserNotFoundException;
import com.Hospital_mang.system.model.Login;
import com.Hospital_mang.system.model.Role;
import com.Hospital_mang.system.model.StaffRecord;
import com.Hospital_mang.system.repository.LoginRepository;
import com.Hospital_mang.system.repository.RoleRepository;
import com.Hospital_mang.system.request.UserRequest;
import com.Hospital_mang.system.response.MessageResponseObject;
import com.Hospital_mang.system.utils.UserUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired
    private GenerateUniqueIDService generateUniqueIDService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private StaffRecordService staffRecordService;
    @Autowired
    private RoleService roleService;

    public Login getUserByUsername(String username) {
        return userUtil.getUserByUsername(username);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public Boolean checkMatchPassword(String password, String comparePassword) {
        return passwordEncoder.matches(password, comparePassword);
    }

    @Transactional
    public ResponseEntity<?> registerStaffRecord(UserRequest request) {
        Login newUser;
        List<Object> responseList = null;
        UserNotFoundException[] exception = {null};
        StaffRecord validateEmail = staffRecordService.findByEmail(request.getEmail());
        if (validateEmail != null) {
            return ResponseEntity.badRequest().body(new MessageResponseObject("Failed! Email already exist" +
                    "", HttpStatus.BAD_REQUEST.value()));
        }
        StaffRecord validatePhone = staffRecordService.findByPhoneNumber(request.getPhoneNumber());
        if (validatePhone != null) {
            return ResponseEntity.badRequest().body(new MessageResponseObject("Failed! Phone number already exist" +
                    "", HttpStatus.BAD_REQUEST.value()));
        }
        try {
            StaffRecord staffRecord = new StaffRecord();
            String staffId = GenerateUniqueIDServiceImpl.zeroPad(generateUniqueIDService.
                    generateUniqueID("staffrecord").getGeneNumber(), 5);
            staffRecord.setStaffId(staffId);
            staffRecord.setFirstName(request.getFirstName());
            staffRecord.setLastName(request.getLastName());
            staffRecord.setEmail(request.getEmail());
            staffRecord.setAddress(request.getAddress());
            staffRecord.setDepartment(departmentService.findDepartmentById(request.getDepartmentId()));
            staffRecord.setCreatedAt(LocalDateTime.now());
            staffRecord.setDateOfBirth(request.getDateOfBirth());
            staffRecord.setGender(request.getGender());
            staffRecord.setDesignationId(request.getDesignationId());
            staffRecord.setStatus("1");
            staffRecord.setPhoneNumber(request.getPhoneNumber());
            staffRecord.setUpdatedAt(LocalDateTime.now());
            boolean savedStaff = false;
            try {
                savedStaff = staffRecordService.saveStaffRecord(staffRecord);
                if (savedStaff == false) {
                    exception[0] = new UserNotFoundException("Failed! Could not save staff detail");
                }
            } catch (Exception e) {
                exception[0] = new UserNotFoundException("Failed! Could not save staff detail");
            }
            if (exception[0] != null) {
                // System.out.println("error here "+exception[0]);
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponseObject(exception[0].getMessage(), HttpStatus.BAD_REQUEST.value()));
            }

            responseList = new ArrayList<>();
//Role role = roleRepository.findById(userRole).orElse(null);
            newUser = new Login();
            newUser.setStaffId(staffId);
            newUser.setEmail(request.getEmail());
            newUser.setPassword(getEncodedPassword(request.getPassword()));
            newUser.setStatus("1");
            newUser.setLockedStatus("0");
            newUser.setPasswordCount(0);
            Set<Role> roleSet = request.getRoleId().stream()
                            .map(item ->{
                                Role  role = roleService.findByRoleId(item);
                                return role;
                            }).collect(Collectors.toSet());
            newUser.setRoles(roleSet);
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
