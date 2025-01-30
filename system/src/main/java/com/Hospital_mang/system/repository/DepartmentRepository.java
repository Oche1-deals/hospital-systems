/**
 * Created By: Innocent Idoko
 * Time:14:08
 */
package com.Hospital_mang.system.repository;

import com.Hospital_mang.system.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,String> {
    Page<Department> findByStatusAndDeleted(int status, boolean deleted, Pageable pageable);
}
