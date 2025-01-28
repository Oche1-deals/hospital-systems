/**
 * Created By: Innocent Idoko
 * Time:16:19
 */
package com.Hospital_mang.system.Service;

import com.Hospital_mang.system.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    public Optional<RefreshToken> findByToken(String token);

    public RefreshToken createRefreshToken(String username);

    public RefreshToken verifyExpiration(RefreshToken token);

    public int deleteByUserId(String userId);
}
