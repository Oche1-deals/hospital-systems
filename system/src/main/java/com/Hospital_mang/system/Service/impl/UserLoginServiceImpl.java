/**
 * Created By: Innocent Idoko
 * Time:16:36
 */
package com.Hospital_mang.system.Service.impl;

import com.Hospital_mang.system.Service.UserLoginService;
import com.Hospital_mang.system.Service.UserService;
import com.Hospital_mang.system.model.Login;
import com.Hospital_mang.system.model.StaffRecord;
import com.Hospital_mang.system.repository.LoginRepository;
import com.Hospital_mang.system.repository.StaffRepository;
import com.Hospital_mang.system.response.MessageResponseObject;
import com.Hospital_mang.system.utils.UserUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import com.Hospital_mang.system.utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private LoginRepository userLoginRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserUtil userUtil;

    @Override
    public MessageResponseObject getActiveUserByEmail(String email) {
        Login userLogin;
        Map<String, Object> responseMap = new HashMap<>();
        MessageResponseObject messageResponseObject = new MessageResponseObject();
        StaffRecord userProfile1 = staffRepository.findByEmailOrPhoneNumberOrStaffId(email, email,email);

        try {
            userLogin = userLoginRepository.findByStaffIdOrEmail(email, email).get();
            // userLogin = userLoginRepository.findByPhoneNumber(phoneNumber).get();
        } catch (Exception e) {
            userLogin = null;

        }
        try {
            if (userProfile1 == null) {
                return new MessageResponseObject("Failed; Invalid username or password", HttpStatus.BAD_REQUEST.value());
            } else if (Objects.equals(userProfile1.getStatus(), "0")) {
                return new MessageResponseObject("Operation not allowed; your account has not been activated", HttpStatus.PARTIAL_CONTENT.value());
            } else if (!Objects.equals(userProfile1.getStatus(), "1")) {
                return new MessageResponseObject("Operation not allowed; your account has not been activated", HttpStatus.PARTIAL_CONTENT.value());
            } else if ((userLogin != null && (Objects.equals(userLogin.getLockedStatus(), "0")))) {
                return new MessageResponseObject("Your account has been locked, kindly reset your account", HttpStatus.LOCKED.value());
            }else if ((userLogin != null && (!Objects.equals(userLogin.getLockedStatus(), "1")))) {
                messageResponseObject.setCode(200);
            }

            responseMap.put("login", userLogin);
            responseMap.put("user", userProfile1);
            messageResponseObject.setMessage("User Valid");
            messageResponseObject.setData(responseMap);

        } catch (Exception e) {
            //System.out.println("error " + e.getMessage());
            return new MessageResponseObject("Error: Validation failed; Kindly check your details and try again", HttpStatus.BAD_REQUEST.value());
        }

        return messageResponseObject;
    }

    @Override
    public boolean changeLoginDetail(Login userLogin) {
        boolean result = false;

        try {
            userLoginRepository.save(userLogin);
            result = true;
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public MessageResponseObject changeUserStatus(String email, String type) {
        return null;
    }
    @Override
    public Login getUserJWTLogin() {
        String authorizationHeader = request.getHeader("Authorization");
        String jwtToken = userUtil.extractJwtToken(authorizationHeader);
        Claims claims = jwtUtil.getAllClaimsFromToken(jwtToken);
        Login userLogin = userLoginRepository.findByStaffIdOrEmail(claims.getSubject(),claims.getSubject()).get();
        return userLogin;
    }
}
