package com.Hospital_mang.system.model;

import com.Hospital_mang.system.model.enummodel.Payment_description;
import com.Hospital_mang.system.model.enummodel.Payment_status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
public class billing_table {


@Id
 private String bill_id;

 private String patient_id;

 private Double total_amount;

 private Payment_description statuse;

 private Payment_status status;

 private LocalDateTime payment_date;

}