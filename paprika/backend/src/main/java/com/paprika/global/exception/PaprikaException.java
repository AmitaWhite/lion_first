package com.paprika.global.exception;

import lombok.Getter;

@Getter
public class PaprikaException extends RuntimeException {

    private final ErrorCode errorCode;

    public PaprikaException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
