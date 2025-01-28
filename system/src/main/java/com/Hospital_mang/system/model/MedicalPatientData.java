package com.Hospital_mang.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class MedicalPatientData {

@Id
 private String record_id;

 private String patient_id;

 private String record_by_staff_id;

 private String diagnosis;

 private String treatment;

 private String appointment_id;

 private String date_visited;

}