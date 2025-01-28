package com.Hospital_mang.system.model;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Pharmacy {

@Id
@Column(name = "medicines_id", nullable = false)
 private String medicinesId;

 private String medicineName;

 private int  quantity;

 private LocalDate expiryDate;

 private Double pricePerUnit;

}