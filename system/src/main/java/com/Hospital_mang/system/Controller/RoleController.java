package com.Hospital_mang.system.Controller;

import com.Hospital_mang.system.Service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
//@CrossOrigin
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
@Tag(name = "Role Controller", description = "Handles role setup like registration, status and delete with retrieval")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Operation(summary = "Department Registration. you are required to provide only the department name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Failed! could not complete operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping({""})
    public ResponseEntity<?> addRole(@Valid @RequestParam String roleDescription){
        return roleService.registerRole(roleDescription);
    }
}
