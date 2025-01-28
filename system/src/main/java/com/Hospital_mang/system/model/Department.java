package com.Hospital_mang.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Department {

@Id
 private String dept_id;

 private String dept_name;

    public void setId(Long departmentId) {
    }
}