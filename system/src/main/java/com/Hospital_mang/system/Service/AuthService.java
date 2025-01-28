/**
 * Created By: Innocent Idoko
 * Time:16:16
 */
package com.Hospital_mang.system.Service;

import com.Hospital_mang.system.request.JwtRequest;
import com.Hospital_mang.system.request.RefreshTokenRequest;
import com.Hospital_mang.system.response.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    void authenticate(String userName, String userPassword);
    JwtResponse LoginUser(JwtRequest jwtRequest);
    public ResponseEntity<?> refreshToken(RefreshTokenRequest request);
}
