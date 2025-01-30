/**
 * Created By: Innocent Idoko
 * Time:17:31
 */
package com.Hospital_mang.system.Controller;

import com.Hospital_mang.system.Service.AuthService;
import com.Hospital_mang.system.Service.UserLoginService;
import com.Hospital_mang.system.Service.UserService;
import com.Hospital_mang.system.request.JwtRequest;
import com.Hospital_mang.system.request.RefreshTokenRequest;
import com.Hospital_mang.system.response.JwtResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin
@RestController
//@CrossOrigin
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Controller", description = "Handles operations related to login, user management and security")
public class AuthenticationController {


    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private AuthService authService;

    @Operation(summary = "Authentication call. Users are to provide their usernames and passwords to enable this operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User  validated"),
            @ApiResponse(responseCode = "404", description = "Username or password not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping({"/login"})
    public JwtResponse Login(@Valid @RequestBody JwtRequest jwtRequest) {
        return authService.LoginUser(jwtRequest);
    }

    @Operation(summary = "Refresh Token. This helps to refresh a jwt token from expiring")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Failed")
    })
    @PostMapping("/refreshtoken")
    //@ApiOperation(value = "This method handles refresh token", response = String.class)
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody RefreshTokenRequest request) {
        return authService.refreshToken(request);
    }
}
