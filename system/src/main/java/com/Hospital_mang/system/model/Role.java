package com.Hospital_mang.system.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Role {

@Id
@Column(name = "role_id", nullable = false)
 private String roleId;

 private String description;

}