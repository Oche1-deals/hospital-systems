package com.Hospital_mang.system.Service;

import org.springframework.http.ResponseEntity;

public interface RoleService {
    ResponseEntity<?> registerRole(String roleDescription);
}
