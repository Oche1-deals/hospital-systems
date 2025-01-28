package com.Hospital_mang.system.Service;

import com.Hospital_mang.system.DTOs.StaffDTO;
import com.Hospital_mang.system.model.Department;
import com.Hospital_mang.system.model.Staff_record;
import com.Hospital_mang.system.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    // Create a new staff record
    public Staff_record createStaff(StaffDTO staffDTO) {
        Staff_record staffRecord = convertDtoToEntity(staffDTO);
        return staffRepository.save(staffRecord);
    }

    // Get all staff records
    public List<Staff_record> getAllStaff() {
        return staffRepository.findAll();
    }
    // Get a specific staff by ID
    public Staff_record getStaffById(String id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + id));
    }

    // Update staff details
    public Staff_record updateStaff(String id, StaffDTO staffDTO) {
        Staff_record existingStaff = getStaffById(id);
        Staff_record updatedStaff = convertDtoToEntity(staffDTO);
        updatedStaff.setStaffId(id); // Ensure ID consistency
        updatedStaff.setCreatedAt(existingStaff.getCreatedAt()); // Preserve createdAt
        return staffRepository.save(updatedStaff);
    }
    // Delete a staff record
    public void deleteStaff(String id) {
        staffRepository.deleteById(id);
    }

    // Convert DTO to Entity
    private Staff_record convertDtoToEntity(StaffDTO staffDTO) {
        Staff_record staffRecord = new Staff_record();
        staffRecord.setFirst_name(staffDTO.getFirstName());
        staffRecord.setLast_name(staffDTO.getLastName());
        staffRecord.setDate_of_birth(staffDTO.getDateOfBirth());
        staffRecord.setAddress(staffDTO.getAddress());
        staffRecord.setEmail(staffDTO.getEmail());
        staffRecord.setGender(staffDTO.getGender());
        staffRecord.setPhone_number(staffDTO.getPhoneNumber());
        staffRecord.setStatus(staffDTO.getStatus());
        staffRecord.setDesignation_id(staffDTO.getDesignationId());

        Department department = new Department();
        department.setId(staffDTO.getDepartmentId());
        staffRecord.setDepartment(department);

        return staffRecord;
    }
}
