package com.Hospital_mang.system.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {

@Id
@Column(name = "role_id", nullable = false)
 private String roleId;

 private String description;

}