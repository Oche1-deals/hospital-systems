package com.Hospital_mang.system.model;

import com.Hospital_mang.system.model.enummodel.Gender;
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

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    private String phoneNumber;

    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String designationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department",
            referencedColumnName = "dept_id")
    private Department department;

}