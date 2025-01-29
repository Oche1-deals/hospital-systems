/**
 * Created By: Innocent Idoko
 * Time:17:26
 */
package com.Hospital_mang.system.dtoconverter;

import com.Hospital_mang.system.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemUserDTO {

    private String userID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String completed;
    private String refrehToken;
    private Boolean specialUser;
    private String townID;
    private String lgaID;
    private String roleId;
    private String roleName;
    private Set<Role> roles;

}

