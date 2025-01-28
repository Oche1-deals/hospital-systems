package com.Hospital_mang.system.repository;

import com.Hospital_mang.system.model.Staff_record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff_record,String> {
}
