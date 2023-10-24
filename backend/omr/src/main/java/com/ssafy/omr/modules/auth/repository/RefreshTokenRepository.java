package com.ssafy.omr.modules.auth.repository;

import com.ssafy.omr.modules.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
