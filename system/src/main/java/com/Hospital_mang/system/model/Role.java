package com.Hospital_mang.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Role {

@Id
 private String role_id;

 private String description;

}