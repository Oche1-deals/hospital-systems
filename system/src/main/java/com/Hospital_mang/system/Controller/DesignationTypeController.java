package com.Hospital_mang.system.Controller;

import com.Hospital_mang.system.Service.DepartmentService;
import com.Hospital_mang.system.Service.DesignationTypeService;
import com.Hospital_mang.system.request.PageItem;
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
@RequestMapping("/api/v1/desigtype")
@RequiredArgsConstructor
@Tag(name = "Designation Type Controller", description = "Handles Designation Type setup like registration, status and delete with retrieval")
public class DesignationTypeController {

    @Autowired
    private DesignationTypeService designationTypeService;
    @Operation(summary = "Designation Type Registration. you are required to provide only the Designation name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Failed! could not complete operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping({""})
    public ResponseEntity<?> addDesignationType(@Valid @RequestParam String designationTypeName){
        return designationTypeService.registerDesignationType(designationTypeName);
    }
    @GetMapping({"/viewAll/{pageNo}/{pageSize}"})
    public ResponseEntity<?> viewAllActiveDesignationType(@PathVariable int pageNo , @PathVariable int pageSize) {
        PageItem pageItem = new PageItem();
        pageItem.setPage(pageNo);
        pageItem.setSize(pageSize);
        return designationTypeService.viewAllActiveDesignationType(pageItem);
    }
}
