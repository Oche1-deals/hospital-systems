/**
 * Created By: Innocent Idoko
 * Time:13:42
 */
package com.Hospital_mang.system.Service.impl;

import com.Hospital_mang.system.Service.RefreshTokenService;
import com.Hospital_mang.system.exception.TokenRefreshException;
import com.Hospital_mang.system.model.Login;
import com.Hospital_mang.system.model.RefreshToken;
import com.Hospital_mang.system.repository.LoginRepository;
import com.Hospital_mang.system.repository.RefreshTokenRepository;
import com.Hospital_mang.system.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Value("${configure.data.jwtExpirationMs}")
    private Long refreshTokenDurationMs;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private LoginRepository userRepository;
    @Autowired
    private UserUtil userUtil;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(String username) {
        System.out.println("RefreshToken "+username);
        Login user = userUtil.getUserByUsername(username);
        System.out.println("user.getUserId() "+user.getStaffId());
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setStaffId(user);
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        return refreshTokenRepository.save(refreshToken);

    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Override
    public int deleteByUserId(String userId) {
        return refreshTokenRepository.deleteByStaffId(userRepository.findById(userId).get());
    }
}
