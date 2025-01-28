package com.Hospital_mang.system.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"password"})
public class Login implements Serializable {

@Id
@Column(name = "staff_id", nullable = false)
 private String staffId;

 private String email;

 private String password;

 private String status;

 private String lockedStatus;

 private int passwordCount;

 private String lastLogin;
 @ManyToOne( fetch = FetchType.EAGER)
 @JoinColumn(name = "role_id",
         referencedColumnName = "role_id")
 private Role roleId;

}