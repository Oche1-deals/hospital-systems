package com.Hospital_mang.system.model;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Laboratory {
 @Id
 @Column(name = "test_id", nullable = false)
 private String testId;

 private String patientId;

 private String testDescription;

 private String result;

 private String sentByStaffId;

 public LocalDate testDate;

}