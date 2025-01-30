/**
 * Created By: Innocent Idoko
 * Time:15:24
 */
package com.Hospital_mang.system.Service;

import com.Hospital_mang.system.model.Login;
import com.Hospital_mang.system.request.UserRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    Login getUserByUsername(String username);
    String getEncodedPassword(String password);
    Boolean checkMatchPassword(String password,String comparePassword);
    ResponseEntity<?> registerStaffRecord(UserRequest request);
}
