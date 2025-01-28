/**
 * Created By: Innocent Idoko
 * Time:15:35
 */
package com.Hospital_mang.system.repository;

import com.Hospital_mang.system.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login,String> {
    Optional<Login> findByEmail(String email);

    Optional<Login> findByStaffId(String staffId);

    Optional<Login> findByStaffIdOrEmail(String staffId, String email);


}