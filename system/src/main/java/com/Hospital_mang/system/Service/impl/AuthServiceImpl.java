/**
 * Created By: Innocent Idoko
 * Time:16:17
 */
package com.Hospital_mang.system.Service.impl;

import com.Hospital_mang.system.Service.AuthService;
import com.Hospital_mang.system.Service.RefreshTokenService;
import com.Hospital_mang.system.Service.UserLoginService;
import com.Hospital_mang.system.Service.UserService;
import com.Hospital_mang.system.dtoconverter.SystemUserDTO;
import com.Hospital_mang.system.exception.TokenRefreshException;
import com.Hospital_mang.system.model.Login;
import com.Hospital_mang.system.model.RefreshToken;
import com.Hospital_mang.system.model.StaffRecord;
import com.Hospital_mang.system.repository.LoginService;
import com.Hospital_mang.system.request.JwtRequest;
import com.Hospital_mang.system.request.RefreshTokenRequest;
import com.Hospital_mang.system.response.JwtResponse;
import com.Hospital_mang.system.response.MessageResponseObject;
import com.Hospital_mang.system.response.TokenRefreshResponse;
import com.Hospital_mang.system.utils.JwtUtil;
import com.Hospital_mang.system.utils.UserUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserLoginService userLoginService;
    @Override
    public JwtResponse LoginUser(JwtRequest jwtRequest) {
        SystemUserDTO systemUserDTO = new SystemUserDTO();
        StaffRecord staffRecord = new StaffRecord();
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        UserDetails userDetails = null;
        RefreshToken refreshToken = null;
        String newGeneratedToken = null;
        Login login = null;
        StaffRecord userProfile = null;
        String authorizationHeader = request.getHeader("Authorization");
        String jwtToken = userUtil.extractJwtToken(authorizationHeader);
        System.out.println("authorizationHeader " + authorizationHeader);
        MessageResponseObject messageResponseObject = userLoginService.getActiveUserByEmail(userName);
               if (messageResponseObject.getCode() != 200) {
            return new JwtResponse(null, null, null,
                    messageResponseObject.getCode(), messageResponseObject.getMessage());
        }
        Map<String, Object> responseMap = (Map<String, Object>) messageResponseObject.getData();
        // login = (UserLogin) messageResponseObject.getData();
        login = (Login) responseMap.get("login");

        //TRYING TO KNOW IF ROLE EXIST
        if (login.getRole() == null || login.getRole().getDescription().equalsIgnoreCase("")) {
            return new JwtResponse(null,
                    null, null, HttpStatus.CONFLICT.value(), "Failed! Invalid account credentials for this operation; Customer account not allowed for this service");

        }

        userProfile = (StaffRecord) responseMap.get("user");
        //System.out.println("userProfile "+new Gson().toJson(userProfile));
        if (userProfile != null) {
            if (userProfile.getStatus() != "1") {
                return new JwtResponse(null,
                        null, null, HttpStatus.CONFLICT.value(), "Invalid account for this operation; Customer account not allowed for this service");
            }

        }
        if ((!userService.checkMatchPassword(jwtRequest.getUserPassword(), login.getPassword()))) {
            if (login.getPasswordCount() < 4) {
                login.setPasswordCount(login.getPasswordCount() + 1);
                if (login.getPasswordCount() == 4) {
                    login.setLockedStatus("1");
                }
                userLoginService.changeLoginDetail(login);
            }

            if (login.getPasswordCount() < 4) {
                return new JwtResponse(null,
                        null, null, HttpStatus.NOT_FOUND.value(), ""
                        + "Invalid user name or password; Login retry count " + (login.getPasswordCount()) + " ."
                        + "Kindly note that your account would be locked after 4 wrong attempts");
            } else if (login.getPasswordCount() >= 4) {
                return new JwtResponse(null,
                        null, null, HttpStatus.NOT_FOUND.value(), ""
                        + "Login Failed; You have entered wrong details more than four times kindly"
                        + " re validate and confirm OTP to continue");
            }

        }

        if (login.getPasswordCount() >= 4) {
            return new JwtResponse(null,
                    null, null, HttpStatus.CONFLICT.value(), "Invalid Operation: You have entered wrong details more then 4 times and your account has been locked"
                    + " Kindly revalidate to re active account");
        }
        if (login.getLockedStatus() == "1") {
            return new JwtResponse(null,
                    null, null, HttpStatus.CONFLICT.value(), "Invalid Operation: Your account has been locked kindly "
                    + "revalidate and enter OTP to re active account");
        }
        if (login.getPasswordCount() == 4) {
            authenticate(userName, userPassword);
        }

        try {
            //System.out.println("userName load " + userName);
            userDetails = customUserDetailsService.loadUserByUsername(userName);
        } catch (Exception e) {
            //System.out.println("error in load " + e.getMessage());
        }
        try {
            refreshToken = refreshTokenService.createRefreshToken(userName);
            // systemUserDTO.setUserID(userName);
            systemUserDTO.setEmail(login.getEmail());
            systemUserDTO.setCompleted("1");
            systemUserDTO.setFirstName(userProfile.getFirstName());
            systemUserDTO.setLastName(userProfile.getLastName());
            systemUserDTO.setPhoneNumber(userProfile.getPhoneNumber());
            systemUserDTO.setUserID(userProfile.getStaffId());
            systemUserDTO.setRefrehToken(refreshToken.getToken());
            systemUserDTO.setRoleId(login.getRole().getRoleId());
            systemUserDTO.setRoleName(login.getRole().getDescription());
                 } catch (Exception e) {
            //System.out.println("createRefreshToken Exception " + e.getMessage());
        }
        newGeneratedToken = jwtUtil.generateToken(userDetails);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        return new JwtResponse(login, newGeneratedToken, refreshToken.getToken(), null);
    }

    @Override
    public ResponseEntity<?> refreshToken(RefreshTokenRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getStaffId)
                .map(user -> {
                    String token = jwtUtil.generateToken(customUserDetailsService.loadUserByUsername(user.getStaffId()));
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @Override
    public void authenticate(String userName, String userPassword) {
        Authentication authentication = null;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userName, userPassword)
            );
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS authenticate", e);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }
}
