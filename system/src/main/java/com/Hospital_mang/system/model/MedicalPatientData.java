package com.Hospital_mang.system.model;

import javax.persistence.*;
import lombok.Data;


@Entity
@Data
public class MedicalPatientData {

@Id
@Column(name = "record_id", nullable = false)
 private String recordId;

 private String patientId;

 private String recordByStaffId;

 private String diagnosis;

 private String treatment;

 private String appointmentId;

 private String dateVisited;

}