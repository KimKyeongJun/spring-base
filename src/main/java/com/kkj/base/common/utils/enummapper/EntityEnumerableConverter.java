package com.kkj.base.common.utils.enummapper;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


//EntityEnumerable을 구현한 클래스에서 DB에 저장될 때 값을 Converting 해주는 공통 클래스 
@Converter
public abstract class EntityEnumerableConverter<T extends EntityEnumerable> implements AttributeConverter<T, String> {

    private final Class<T> clazz;

    protected EntityEnumerableConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        if (ObjectUtils.isEmpty(attribute)) {
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        if (StringUtils.isBlank(dbData)) {
            return null;
        }
        T[] enumConstants = clazz.getEnumConstants();
        for (T constant : enumConstants) {
            if (StringUtils.equals(constant.getCode(), dbData)) {
                return constant;
            }
        }

        throw new UnsupportedOperationException(String.format("%s 지원하지 않는 enum 형식입니다.", dbData));
    }
}
