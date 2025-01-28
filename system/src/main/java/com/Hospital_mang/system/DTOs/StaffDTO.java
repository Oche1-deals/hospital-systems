package com.Hospital_mang.system.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffDTO {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String address;
    private String email;
    private String gender;
    private int phoneNumber;
    private String status;
    private String designationId;
    private Long departmentId; // Reference to the department
}
