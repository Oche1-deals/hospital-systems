/**
 * Created By: Innocent Idoko
 * Time:15:33
 */
package com.Hospital_mang.system.utils;

import com.Hospital_mang.system.exception.UserNotFoundException;
import com.Hospital_mang.system.model.Login;
import com.Hospital_mang.system.model.StaffRecord;
import com.Hospital_mang.system.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserUtil {

    @Autowired
    private final LoginRepository loginRepository;
    @Autowired
    private StaffRecord systemuserRepository;

    public Login getLoggedInUser() throws UserNotFoundException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String loggedInUserUserName = userDetails.getUsername();
        //System.out.println("loggedInUserUserName getLoggedInUser " + loggedInUserUserName);
        Login userDetail = getUserByUsername(loggedInUserUserName);
        if (userDetail == null) {
            new BadCredentialsException("INVALID_CREDENTIALS");
        }
        return userDetail;
    }

    public Login getUserByUsername(String username) {
        StaffRecord userProfile = new StaffRecord();
        userProfile.setStaffId(username);
        userProfile.setEmail(username);

        return loginRepository.findByStaffIdOrEmail(username, username)
                .orElseThrow(() -> new BadCredentialsException("INVALID_CREDENTIALS"));
//return null;
    }

    public String getTimestamp() {
        Date date = new Date();
        long time = date.getTime();
        return String.valueOf(time);
    }


    public String extractJwtToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}

