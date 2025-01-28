package com.Hospital_mang.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
@Entity
@Data
public class prescription {

@Id
 private String prescription_id;
@ManyToOne
 private PatientProfile patient_id;
@ManyToOne
 private Staff_record prescribe_by_staff_id;

 private String medicine_name;

 private String dosage;

 private LocalDate prescribed_date;

 private String appointment_id;

}