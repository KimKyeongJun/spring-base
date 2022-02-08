package com.kkj.base.common.utils.enummapper;

import lombok.Getter;

@Getter
public class EnumMapperValue {

    private String key;
    private String value;

    public EnumMapperValue(EnumMapperType  enumMapperType) {
        key = enumMapperType.getKey();
        value = enumMapperType.getValue();
    }

    @Override
    public String toString() {
        return  "{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}