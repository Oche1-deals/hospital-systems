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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
//@CrossOrigin
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Api(value = "", tags = {"Authentication Service"})
@Tag(name = "Authentication Service", description = "This class handles all authentication within users account, including login")
public class AuthenticationController {

    @Autowired
    private final UserService userService;
    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private AuthService authService;

    @ApiOperation(value = "login user authentication.")
    @ApiResponse(code = 200, message = "created")
    @PostMapping({"/login"})
    public JwtResponse Login(@Valid @RequestBody JwtRequest jwtRequest) {
        return authService.LoginUser(jwtRequest);
    }

    @PostMapping("/refreshtoken")
    @ApiOperation(value = "This method handles refresh token", response = String.class)
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody RefreshTokenRequest request) {
        return authService.refreshToken(request);
    }
}
