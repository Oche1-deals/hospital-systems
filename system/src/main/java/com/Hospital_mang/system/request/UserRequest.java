/**
 * Created By: Innocent Idoko
 * Time:15:30
 */
package com.Hospital_mang.system.request;

import com.Hospital_mang.system.model.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Data
@Setter
@Getter
public class UserRequest {
    private String id;

    @NotNull(message = "username cannot be null")
    @NotEmpty(message = "username cannot be empty")
    private String username;

    @NotNull(message = "first name cannot be null")
    @NotEmpty(message = "first name cannot be empty")
    private String firstName;

    @NotNull(message = "last name cannot be null")
    @NotEmpty(message = "last anme cannot be empty")
    private String lastName;

    @NotNull(message = "email cannot be null")
    @NotEmpty(message = "email cannot be empty")
    private String email;

    @NotNull(message = "password cannot be null")
    @NotEmpty(message = "password cannot be empty")
    private String password;

//    @NotNull(message = "role cannot be null")
//    private String role;
    private Set<Role> roles;



}


