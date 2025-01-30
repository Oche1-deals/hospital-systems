package com.Hospital_mang.system.model;

import com.Hospital_mang.system.model.enummodel.Gender;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PatientProfile {

@Id
@Column(name = "patient_id", nullable = false)
 private String patientId;

 private String firstName;

 private String lastName;
 @Enumerated(EnumType.ORDINAL)
 private Gender gender;

 private String dob;

 private String phone;

 private String address;

 private String bloodGroup;

 private String emergencyContact;

 private String status;

}