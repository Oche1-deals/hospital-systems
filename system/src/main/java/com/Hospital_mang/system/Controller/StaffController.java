package com.Hospital_mang.system.Controller;

import com.Hospital_mang.system.DTOs.StaffDTO;
import com.Hospital_mang.system.Service.StaffService;
import com.Hospital_mang.system.model.StaffRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StaffController {
    @Autowired
    private StaffService staffService;

    // Create new staff
    @PostMapping
    public ResponseEntity<StaffRecord> createStaff(@RequestBody StaffDTO staffDTO) {
        StaffRecord newStaff = staffService.createStaff(staffDTO);
        return ResponseEntity.ok(newStaff);
    }
    // Get all staff
    @GetMapping("/staff")
    public ResponseEntity<List<StaffRecord>> getAllStaff() {
        List<StaffRecord> staffList = staffService.getAllStaff();
        return ResponseEntity.ok(staffList);
    }

    // Get staff by ID
    @GetMapping("/staff/{id}")
    public ResponseEntity<StaffRecord> getStaffById(@PathVariable String id) {
        StaffRecord staff = staffService.getStaffById(id);
        return ResponseEntity.ok(staff);
    }
    // Update staff
    @PutMapping("/{id}")
    public ResponseEntity<StaffRecord> updateStaff(@PathVariable String id, @RequestBody StaffDTO staffDTO) {
        StaffRecord updatedStaff = staffService.updateStaff(id, staffDTO);
        return ResponseEntity.ok(updatedStaff);
    }

    // Delete staff
    @DeleteMapping("/delete_staff/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable String id) {
        staffService.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }

}
