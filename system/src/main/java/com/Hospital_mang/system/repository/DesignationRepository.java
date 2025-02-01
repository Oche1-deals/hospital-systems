package com.Hospital_mang.system.repository;

import com.Hospital_mang.system.model.Department;
import com.Hospital_mang.system.model.Desigination;
import com.Hospital_mang.system.model.Login;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignationRepository extends JpaRepository<Desigination,String> {
    Page<Desigination> findByStatusAndDeleted(int i, Boolean aFalse, Pageable paging);
}
