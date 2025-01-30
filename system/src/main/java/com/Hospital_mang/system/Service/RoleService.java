package com.Hospital_mang.system.Service;

import com.Hospital_mang.system.model.Role;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    ResponseEntity<?> registerRole(String roleDescription);
    Role findByRoleId(String roleId);
}
