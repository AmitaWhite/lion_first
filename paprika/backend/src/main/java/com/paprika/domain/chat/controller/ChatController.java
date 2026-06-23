package com.paprika.domain.chat.controller;

import com.paprika.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

/**
 * 채팅 컨트롤러 (REST + WebSocket)
 * 담당: C - 한대천
 *
 * [REST API]
 * TODO:
 *  - GET  /api/v1/chat/rooms          내 채팅방 목록
 *  - GET  /api/v1/chat/rooms/{id}     채팅방 생성 또는 조회 (상품ID + 상대방ID)
 *  - GET  /api/v1/chat/rooms/{id}/messages  이전 메시지 조회 (무한 스크롤)
 *  - GET  /api/v1/notifications       알림 목록
 *
 * [WebSocket - STOMP]
 * TODO:
 *  - @MessageMapping("/chat/{roomId}")  메시지 전송 → /topic/chat/{roomId} 브로드캐스트
 */
@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {

    // TODO: private final ChatService chatService;

    @GetMapping("/rooms")
    public ResponseEntity<ApiResponse<Object>> getChatRooms() {
        // TODO: 구현
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<ApiResponse<Object>> getChatRoom(@PathVariable Long roomId) {
        // TODO: 구현
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @GetMapping("/rooms/{roomId}/messages")
    public ResponseEntity<ApiResponse<Object>> getMessages(
            @PathVariable Long roomId,
            @PageableDefault(size = 30) Pageable pageable
    ) {
        // TODO: 무한 스크롤 구현 (cursor 방식 고려)
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    // WebSocket 메시지 핸들러
    @MessageMapping("/chat/{roomId}")
    public void sendMessage(@Payload Object messageRequest /* ChatMessageRequest */) {
        // TODO: 메시지 저장 + /topic/chat/{roomId} 브로드캐스트
        // TODO: 상대방에게 실시간 알림 전송 /user/{userId}/notification
    }
}
