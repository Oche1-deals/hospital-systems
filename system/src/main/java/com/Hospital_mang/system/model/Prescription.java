package com.Hospital_mang.system.model;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Prescription {

@Id
@Column(name = "prescription_id", nullable = false)
 private String prescriptionId;
@ManyToOne
@JoinColumn(name = "patient_id",
        referencedColumnName = "patient_id")
 private PatientProfile patientId;
@ManyToOne
@JoinColumn(name = "staff_id",
        referencedColumnName = "staff_id")
 private StaffRecord staffId;

 private String medicineName;

 private String dosage;

 private LocalDate prescribedDate;

 private String appointmentId;

}