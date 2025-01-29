/**
 * Created By: Innocent Idoko
 * Time:16:24
 */
package com.Hospital_mang.system.response;

import com.Hospital_mang.system.model.Login;
import lombok.Data;

import java.util.Map;

@Data
public class JwtResponse {
    private Login user;
    private String jwtToken;
    private int code = 201;
    private String refreshToken;
    private Map<String,Object> data;
    private String message = "login successful";



    public JwtResponse(Login user, String jwtToken, String refreshToken, Map<String, Object> data) {
        this.user = user;
        this.jwtToken = jwtToken;
        this.refreshToken = refreshToken;
        this.data = data;
    }
    public JwtResponse( String jwtToken, String refreshToken, Map<String, Object> data) {

        this.jwtToken = jwtToken;
        this.refreshToken = refreshToken;
        this.data = data;
    }
    public JwtResponse(Login user, String jwtToken, String refreshToken, Map<String, Object> data,int code,String message) {
        this.user = user;
        this.jwtToken = jwtToken;
        this.refreshToken = refreshToken;
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public JwtResponse(Login user, String jwtToken, String refreshToken) {
        this.user = user;
        this.jwtToken = jwtToken;
        this.refreshToken = refreshToken;
    }
    public JwtResponse(Login user, String jwtToken, String refreshToken,int code,String message) {
        this.user = user;
        this.jwtToken = jwtToken;
        this.refreshToken = refreshToken;
        this.code = code;
        this.message = message;
    }

    public Login getUser() {
        return user;
    }
}