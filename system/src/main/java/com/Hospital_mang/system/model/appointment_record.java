package com.Hospital_mang.system.model;

import com.Hospital_mang.system.model.enummodel.AppointmentEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class appointment_record   {


@Id
 private String appointment_id;
@ManyToOne
@JoinColumn(name = "patient_id",referencedColumnName = "patient_id")
 private PatientProfile patient_id;
@ManyToOne
 private Designation_type designation_type_id;

private LocalDateTime appointment_date;

 private String blood_pressure;

 private String sugar_level;

 private String weight;

 private String temperature;

 private AppointmentEnum status;

}