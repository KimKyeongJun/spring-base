package com.kkj.base.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kkj.base.common.utils.enummapper.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorMessage implements EnumMapperType {

    // COMMON
    INVALID_CODE(400, "C001", "Invalid Code"),
    RESOURCE_NOT_FOUND(404, "C002", "Resource not found"),
    EXPIRED_CODE(400, "C003", "Expired Code"),
    TEMPORARY_SERVER_ERROR(400, "C004", "Temporary Server Error");

    private int status;
    private String code;
    private String message;
    private String detail;
    private String uuid;

    ErrorMessage(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    @Override
    public String getKey() {
        return this.code;
    }

    @Override
    public String getValue() {
        return this.message;
    }
}
