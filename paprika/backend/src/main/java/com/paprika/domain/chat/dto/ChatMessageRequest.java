package com.paprika.domain.chat.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 채팅 메시지 전송 DTO (WebSocket STOMP payload)
 * 담당: C - 한대천
 */
@Getter
public class ChatMessageRequest {

    private Long chatRoomId;

    private Long senderId;

    @NotBlank
    private String content;
}
