/**
 * Created By: Innocent Idoko
 * Time:14:41
 */
package com.Hospital_mang.system.Controller;

import com.Hospital_mang.system.Service.DepartmentService;
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
@RequestMapping("/api/v1/dept")
@RequiredArgsConstructor
@Tag(name = "Department Controller", description = "Handles departmental setup like registration, status and delete with retrieval")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Operation(summary = "Department Registration. you are required to provide only the department name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Failed! could not complete operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping({""})
    public ResponseEntity<?> addDepartment(@Valid @RequestParam String departmentName){
        return departmentService.registerDepartment(departmentName);
    }

    @GetMapping({"/viewAll/{pageNo}/{pageSize}"})
    public ResponseEntity<?> viewAllActiveDepartment(@PathVariable int pageNo , @PathVariable int pageSize){
        PageItem  pageItem = new PageItem();
        pageItem.setPage(pageNo);
        pageItem.setSize(pageSize);
        return departmentService.viewAllActiveDepartment(pageItem);
    }
}
