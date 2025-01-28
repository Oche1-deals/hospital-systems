package com.Hospital_mang.system.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Desigination {


@Id
@Column(name = "designation_id", nullable = false)
 private String designationId;

 private String description;

}