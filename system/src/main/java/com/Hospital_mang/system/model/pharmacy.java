package com.Hospital_mang.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
public class pharmacy {

@Id
 private String medicines_id;

 private String medicine_name;

 private int  quantity;

 private LocalDate expiry_date;

 private Double price_per_unit;

}