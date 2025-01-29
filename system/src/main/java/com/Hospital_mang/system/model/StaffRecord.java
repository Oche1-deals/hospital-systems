package com.Hospital_mang.system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffRecord {

    @Id
    @Column(name = "staff_id", nullable = false)
    private String staffId;

    private String firstName;

    private String lastName;

    private String dateOfBirth;

    private String address;
    private String email;

    private String gender;

    private String phoneNumber;

    private String status;

    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
    private String designationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department",
            referencedColumnName = "dept_id")
    private Department department;

}