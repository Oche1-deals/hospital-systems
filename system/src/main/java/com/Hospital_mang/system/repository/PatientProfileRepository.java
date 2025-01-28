package com.Hospital_mang.system.repository;

import com.Hospital_mang.system.model.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientProfileRepository extends JpaRepository<PatientProfile,String> {
}
