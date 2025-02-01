package com.Hospital_mang.system.Controller;

import com.Hospital_mang.system.Service.DepartmentService;
import com.Hospital_mang.system.Service.DesignationService;
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
@RequestMapping("/api/v1/desig")
@RequiredArgsConstructor
@Tag(name = "Designation Controller", description = "Handles designation setup like registration, status and delete with retrieval")

public class DesignationController {
    @Autowired
    private DesignationService designationService;

    @Operation(summary = "Designation Registration. you are required to provide only the designation name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Failed! could not complete operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping({""})
    public ResponseEntity<?> addDepartment(@Valid @RequestParam String designationName){
        return designationService.registerDesignation(designationName);
    }
}
