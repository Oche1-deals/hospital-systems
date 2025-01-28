package com.Hospital_mang.system.repository;

import com.Hospital_mang.system.model.StaffRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffRecord,String> {
    public StaffRecord findByEmailOrPhoneNumber(String email, String phoneNumber);
}
