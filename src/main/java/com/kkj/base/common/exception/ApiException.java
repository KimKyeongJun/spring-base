package com.kkj.base.common.exception;

public class ApiException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public ApiException(String message, ErrorMessage errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }

    public ApiException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorCode() {
        return this.errorMessage;
    }
}
