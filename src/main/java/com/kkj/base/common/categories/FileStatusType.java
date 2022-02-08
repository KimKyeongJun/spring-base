package com.kkj.base.common.categories;

import com.kkj.base.common.utils.enummapper.EnumMapperType;

public enum FileStatusType implements EnumMapperType {
    P("전송중"),
    C("완료"),
    F("실패");

    private String value;

    FileStatusType(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}