/**
 * Created By: Innocent Idoko
 * Time:16:33
 */
package com.Hospital_mang.system.repository;

import com.Hospital_mang.system.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginService extends JpaRepository<Login,String> {
}
