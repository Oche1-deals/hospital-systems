package com.Hospital_mang.system.model;

import com.Hospital_mang.system.model.enummodel.AppointmentEnum;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class AppointmentRecord {


@Id
@Column(name = "appointment_id", nullable = false)
 private String appointmentId;
@ManyToOne
@JoinColumn(name = "patient_id",referencedColumnName = "patient_id")
 private PatientProfile patientId;
@ManyToOne
 private DesignationType designationTypeId;

private LocalDateTime appointmentDate;

 private String bloodPressure;

 private String sugarLevel;

 private String weight;

 private String temperature;
 @Enumerated(EnumType.ORDINAL)
 private AppointmentEnum status;

}