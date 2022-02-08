package com.kkj.base.common.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private String code;
    private int status;
    private String detail;
    private ZonedDateTime zonedDateTime;
    private String uuid;

    public ErrorResponse(ErrorMessage errMsg) {
        this.message = errMsg.getMessage();
        this.status = errMsg.getStatus();
        this.code = errMsg.getCode();
        this.detail = errMsg.getDetail();
        this.uuid = errMsg.getUuid();
    }

    public static ErrorResponse of(ErrorMessage errorMessage) {
        return new ErrorResponse(errorMessage);
    }
}
