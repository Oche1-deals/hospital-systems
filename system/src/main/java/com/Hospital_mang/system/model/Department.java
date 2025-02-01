package com.Hospital_mang.system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @Column(name = "dept_id", nullable = false)
    private String deptId;

    private String deptName;

    private int status;//1 is active , 0 is invactive
    private boolean deleted;
    private String staffId;


}