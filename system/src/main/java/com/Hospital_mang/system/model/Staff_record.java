package com.Hospital_mang.system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff_record {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private String staffId;

 private String first_name;

 private String last_name;

 private String date_of_birth;

 private String address;

 private String email;

 private String gender;

 private int phone_number;

 private String status;

 @Column(nullable = false, updatable = false)
 private LocalDateTime createdAt;

 @Column(nullable = false)
 private LocalDateTime updatedAt;

 @PrePersist
 protected void onCreate() {
  this.createdAt = LocalDateTime.now();
  this.updatedAt = LocalDateTime.now();
 }
 @PreUpdate
 protected void onUpdate() {
  this.updatedAt = LocalDateTime.now();
 }

 private String designation_id;

 @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
 private Department department;

}