package com.paprika.domain.auth.service;

import com.paprika.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 인증 서비스
 * 담당: A - 민동현
 *
 * TODO:
 *  - 회원가입 로직 (이메일 중복 체크, 비밀번호 암호화)
 *  - 로그인 로직 (JWT Access/Refresh 토큰 발급)
 *  - 토큰 재발급 로직 (Refresh Token 검증)
 *  - 로그아웃 로직 (Refresh Token 블랙리스트 or 삭제)
 *  - OAuth2 소셜 로그인 후처리
 *  - 관리자 계정 접근 로직
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    // TODO: private final JwtProvider jwtProvider;

}
