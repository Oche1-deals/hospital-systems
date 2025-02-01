/**
 * Created By: Innocent Idoko
 * Time:14:10
 */
package com.Hospital_mang.system.Service.impl;

import com.Hospital_mang.system.Service.DepartmentService;
import com.Hospital_mang.system.Service.GenerateUniqueIDService;
import com.Hospital_mang.system.Service.UserLoginService;
import com.Hospital_mang.system.model.Department;
import com.Hospital_mang.system.model.Login;
import com.Hospital_mang.system.repository.DepartmentRepository;
import com.Hospital_mang.system.request.PageItem;
import com.Hospital_mang.system.response.MessageResponseObject;
import com.Hospital_mang.system.response.dto.ConvertedDepartmentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private GenerateUniqueIDService generateUniqueIDService;
    @Autowired
    private UserLoginService userLoginService;

    @Override
    public ResponseEntity<?> registerDepartment(String departmentName) {
        String deptID = GenerateUniqueIDServiceImpl.zeroPad(generateUniqueIDService.
                generateUniqueID("dept").getGeneNumber(), 5);
        Login staffRcord = userLoginService.getUserJWTLogin();

        Department department = new Department();
        department.setDeptName(departmentName);
        department.setStatus(1);
        department.setDeleted(Boolean.FALSE);
        department.setDeptId(deptID);
        department.setStaffId(staffRcord.getStaffId());
        Department savedDept = departmentRepository.save(department);

        if (savedDept == null) {
            return ResponseEntity.badRequest().body(new MessageResponseObject("Failed! Could not register deptment. Contact an Admin" +
                    "", HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.ok().body(new MessageResponseObject
                ("Successful! Department Registered", HttpStatus.CREATED.value(), savedDept));
    }

    @Override
    public ResponseEntity<?> viewAllActiveDepartment(PageItem pageItem) {
        Pageable paging = PageRequest.of(pageItem.getPage(), pageItem.getSize());
        Page<Department> viewAllDepartment = departmentRepository.findByStatusAndDeleted(1, Boolean.FALSE, paging);
        if (viewAllDepartment.getContent().isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponseObject("No Department found", HttpStatus.BAD_REQUEST.value()));
        }
        Map<String, Object> response = new HashMap<>();
        response.put("data",
                ConvertedDepartmentResponseDTO.departmentMultiResponse(viewAllDepartment.getContent()));
        response.put("total", viewAllDepartment.getTotalElements());
        response.put("currentPage", viewAllDepartment.getNumber());
        response.put("totalPage", viewAllDepartment.getTotalPages());


        return ResponseEntity.accepted().body(new MessageResponseObject("Department found", HttpStatus.OK.value(), response));
    }

    @Override
    public Department findDepartmentById(String departmentId) {
        return departmentRepository.findById(departmentId).get();
    }
}
