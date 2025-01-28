package com.Hospital_mang.system.model;

import javax.persistence.*;
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

}