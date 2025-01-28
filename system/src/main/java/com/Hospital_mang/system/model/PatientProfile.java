package com.Hospital_mang.system.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class PatientProfile {

@Id
@Column(name = "patient_id", nullable = false)
 private String patientId;

 private String firstName;

 private String lastName;

 private String gender;

 private String dob;

 private String phone;

 private String address;

 private String bloodGroup;

 private Integer emergencyContact;

 private String status;

}