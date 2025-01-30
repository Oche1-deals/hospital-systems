/**
 * Created By: Innocent Idoko
 * Time:16:35
 */
package com.Hospital_mang.system.Service;

import com.Hospital_mang.system.model.Login;
import com.Hospital_mang.system.response.MessageResponseObject;

public interface UserLoginService {
    public MessageResponseObject getActiveUserByEmail(String email);

    public boolean changeLoginDetail(Login userLogin);

    MessageResponseObject changeUserStatus(String email, String type);

    Login getUserJWTLogin();
}
