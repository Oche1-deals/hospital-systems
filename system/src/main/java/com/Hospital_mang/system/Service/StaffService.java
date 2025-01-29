package com.Hospital_mang.system.Service;

import com.Hospital_mang.system.DTOs.StaffDTO;
import com.Hospital_mang.system.model.StaffRecord;
import com.Hospital_mang.system.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    // Create a new staff record
    public StaffRecord createStaff(StaffDTO staffDTO) {
        StaffRecord staffRecord = convertDtoToEntity(staffDTO);
        return staffRepository.save(staffRecord);
    }

    // Get all staff records
    public List<StaffRecord> getAllStaff() {
        return staffRepository.findAll();
    }
    // Get a specific staff by ID
    public StaffRecord getStaffById(String id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + id));
    }

    // Update staff details
    public StaffRecord updateStaff(String id, StaffDTO staffDTO) {
//        StaffRecord existingStaff = getStaffById(id);
//        StaffRecord updatedStaff = convertDtoToEntity(staffDTO);
//        updatedStaff.setStaffId(id); // Ensure ID consistency
//        updatedStaff.setCreatedAt(existingStaff.getCreatedAt()); // Preserve createdAt
//        return staffRepository.save(updatedStaff);
        return null;
    }
    // Delete a staff record
    public void deleteStaff(String id) {
        staffRepository.deleteById(id);
    }

    // Convert DTO to Entity
    private StaffRecord convertDtoToEntity(StaffDTO staffDTO) {
//        StaffRecord staffRecord = new StaffRecord();
//        staffRecord.setFirst_name(staffDTO.getFirstName());
//        staffRecord.setLast_name(staffDTO.getLastName());
//        staffRecord.setDate_of_birth(staffDTO.getDateOfBirth());
//        staffRecord.setAddress(staffDTO.getAddress());
//        staffRecord.setEmail(staffDTO.getEmail());
//        staffRecord.setGender(staffDTO.getGender());
//        staffRecord.setPhone_number(staffDTO.getPhoneNumber());
//        staffRecord.setStatus(staffDTO.getStatus());
//        staffRecord.setDesignation_id(staffDTO.getDesignationId());
//
//        Department department = new Department();
//        department.setId(staffDTO.getDepartmentId());
//        staffRecord.setDepartment(department);
//
//        return staffRecord;
        return null;
    }
}
