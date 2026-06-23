package com.paprika.domain.transaction.service;

import com.paprika.domain.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 거래 서비스
 * 담당: D - 이동준
 *
 * TODO:
 *  - 거래 생성 (직거래/택배 분기)
 *  - 직거래: 약속 장소(지도) 지정 및 최종 거래 완료 프로세스
 *  - 택배: 운송장 번호 입력 및 배송 상태 변경 로직
 *  - 거래 상태 전이 검증 (FSM: PENDING→AGREED→IN_TRANSIT→COMPLETED)
 *  - 거래 완료 시 Product 상태를 SOLD로 변경 (B - 백성민과 연동)
 *  - 세금계산서 발행 로직
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionService {

    private final TransactionRepository transactionRepository;
    // TODO: private final ProductService productService; (B - 백성민 의존성)
}
