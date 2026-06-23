package com.paprika.domain.chat.service;

import com.paprika.domain.chat.repository.ChatMessageRepository;
import com.paprika.domain.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 채팅 서비스
 * 담당: C - 한대천
 *
 * TODO:
 *  - 채팅방 생성/조회 (구매자+판매자+상품으로 중복 방지)
 *  - 메시지 저장 및 WebSocket 브로드캐스트
 *  - 이전 메시지 페이징 조회 (무한 스크롤)
 *  - 실시간 알림 발송 (새 메시지 도착 시)
 *  - 메시지 읽음 처리
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final SimpMessagingTemplate messagingTemplate;
}
