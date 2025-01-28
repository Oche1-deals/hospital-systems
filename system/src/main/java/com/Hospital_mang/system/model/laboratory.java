package com.Hospital_mang.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
public class laboratory {
 @Id
 private String test_id;

 private String patient_id;

 private String test_description;

 private String result;

 private String sent_by_staff_id;

 public LocalDate test_date;

}