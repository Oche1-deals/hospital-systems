package com.Hospital_mang.system.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DesignationType {

@Id
@Column(name = "id", nullable = false)
 public String id;

 public String description;

}