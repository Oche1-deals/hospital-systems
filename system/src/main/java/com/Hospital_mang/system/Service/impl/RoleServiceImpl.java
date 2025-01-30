package com.Hospital_mang.system.Service.impl;

import com.Hospital_mang.system.Service.GenerateUniqueIDService;
import com.Hospital_mang.system.Service.RoleService;
import com.Hospital_mang.system.model.Department;
import com.Hospital_mang.system.model.Role;
import com.Hospital_mang.system.repository.DepartmentRepository;
import com.Hospital_mang.system.repository.RoleRepository;
import com.Hospital_mang.system.response.MessageResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private GenerateUniqueIDService generateUniqueIDService;


    @Override
    public ResponseEntity<?> registerRole(String roleDescription) {
        String roleId = GenerateUniqueIDServiceImpl.zeroPad(generateUniqueIDService.
                generateUniqueID("role").getGeneNumber(), 5);
        Role role = new Role();
        role.setDescription(roleDescription);
        role.setStatus(1);
        role.setDeleted(Boolean.FALSE);
        role.setRoleId(roleId);
        Role savedRole = roleRepository.save(role);
        if (savedRole == null) {
            return ResponseEntity.badRequest().body(new MessageResponseObject("Failed! Could not register role. Contact an Admin" +
                    "", HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.ok().body(new MessageResponseObject
                ("Successful! role Registered", HttpStatus.CREATED.value(), savedRole));
    }

    @Override
    public Role findByRoleId(String roleId) {
        return roleRepository.findById(roleId).get();
    }
}
