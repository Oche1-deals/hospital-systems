package com.Hospital_mang.system.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"password"})
@Table(name = "logins")
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
// @ManyToOne( fetch = FetchType.EAGER)
// @JoinColumn(name = "role_id",
//         referencedColumnName = "role_id")
// private Role roleId;


 @ManyToMany(fetch = FetchType.EAGER)
 @JoinTable(name = "logins_roles",
         joinColumns = @JoinColumn(name = "staff_id", referencedColumnName = "staff_id"),
         inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
 )
 private Set<Role> roles;

}