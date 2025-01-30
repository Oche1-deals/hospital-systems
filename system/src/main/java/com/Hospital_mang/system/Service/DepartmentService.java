/**
 * Created By: Innocent Idoko
 * Time:14:10
 */
package com.Hospital_mang.system.Service;

import com.Hospital_mang.system.request.PageItem;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

public interface DepartmentService {
    ResponseEntity<?> registerDepartment(String departmentName);
    ResponseEntity<?> viewAllActiveDepartment(PageItem pageItem);
}
