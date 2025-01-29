/**
 * Created By: Innocent Idoko
 * Time:16:27
 */
package com.Hospital_mang.system.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {

    @NotBlank(message = "Username must not be null or empty")
    private String userName;
    @NotBlank
    private String userPassword;


}