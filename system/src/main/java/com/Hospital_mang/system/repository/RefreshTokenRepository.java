/**
 * Created By: Innocent Idoko
 * Time:13:47
 */
package com.Hospital_mang.system.repository;

import com.Hospital_mang.system.model.Login;
import com.Hospital_mang.system.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByStaffId(Login user);
}
