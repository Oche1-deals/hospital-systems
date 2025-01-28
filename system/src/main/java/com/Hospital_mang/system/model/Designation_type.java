package com.Hospital_mang.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Designation_type {

@Id
 public String id;

 public String description;

}