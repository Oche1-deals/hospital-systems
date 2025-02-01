/**
 * Created By: Innocent Idoko
 * Time:15:30
 */
package com.Hospital_mang.system.request;

import com.Hospital_mang.system.model.Role;
import com.Hospital_mang.system.model.enummodel.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Data
@Setter
@Getter
public class UserRequest {


    @NotNull(message = "phone number cannot be null")
    @NotEmpty(message = "phone number cannot be empty")
    @Pattern(regexp = "\\+?[0-9]{7,15}", message = "invalid phone number format")
    private String phoneNumber;

    @NotNull(message = "first name cannot be null")
    @NotEmpty(message = "first name cannot be empty")
    private String firstName;

    @NotNull(message = "last name cannot be null")
    @NotEmpty(message = "last anme cannot be empty")
    private String lastName;

    @NotNull(message = "email cannot be null")
    @NotEmpty(message = "email cannot be empty")
    @Email(message = "invalid email format")
    private String email;

    @NotNull(message = "password cannot be null")
    @NotEmpty(message = "password cannot be empty")
    private String password;

//    @NotNull(message = "role cannot be null")//Gender
//    private String role;
    private Set<String> roleId;

    @NotNull(message = "department cannot be null")
    @NotEmpty(message = "department cannot be empty")
    private String departmentId;

    @NotNull(message = "date of birth cannot be null")
    @NotEmpty(message = "date of birth cannot be empty")
    private String dateOfBirth;
    private String address;
    @NotNull(message = "gender cannot be null")
    private Gender gender;
    private String designationId;


}


