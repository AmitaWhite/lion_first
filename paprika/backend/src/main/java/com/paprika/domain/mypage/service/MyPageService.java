package com.paprika.domain.mypage.service;

import com.paprika.domain.mypage.repository.ReviewRepository;
import com.paprika.domain.mypage.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 마이페이지 서비스
 * 담당: E - 장인호
 *
 * TODO:
 *  - 프로필 조회/수정
 *  - 나의 거래 내역 조회 (판매중/예약중/완료 필터)
 *  - 관심 상품(찜) 추가/제거/목록 조회
 *  - 거래 후기 작성 (거래 완료 상태 검증 필요 - D - 이동준과 연동)
 *  - 매너 온도(신뢰 점수) 업데이트 로직
 *  - 공지사항 CRUD (Admin)
 *  - 카테고리 관리 (Admin)
 *  - 신고 접수 처리 및 유저 제어 (Admin)
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {

    private final ReviewRepository reviewRepository;
    private final WishListRepository wishListRepository;
}
