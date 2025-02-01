package com.Hospital_mang.system.repository;

import com.Hospital_mang.system.model.Desigination;
import com.Hospital_mang.system.model.DesignationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationTypeRepository extends JpaRepository<DesignationType,String> {

    Page<DesignationType> findByStatusAndDeleted(int i, Boolean aFalse, Pageable paging);
}
