package com.Hospital_mang.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PatientProfile {

@Id
 private String patient_id;

 private String first_name;

 private String last_name;

 private String gender;

 private String dob;

 private String phone;

 private String address;

 private String blood_group;

 private Integer emergency_contact;

 private String status;

}