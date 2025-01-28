package com.Hospital_mang.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class login {

@Id
 private String staff_id;

 private String email;

 private String password;

 private String status;

 private String locked_status;

 private int password_count;

 private String last_login;
 @ManyToOne
 private Role role;

}