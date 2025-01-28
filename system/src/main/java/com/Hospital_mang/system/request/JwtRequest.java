/**
 * Created By: Innocent Idoko
 * Time:16:27
 */
package com.Hospital_mang.system.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class JwtRequest {

    @NotBlank(message = "Username must not be null or empty")
    private String userName;
    @NotBlank
    private String userPassword;
//    @NotBlank
//    private String deviceId;

}