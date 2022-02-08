package com.kkj.base.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ObjectUtils;

public class MappingUtil {

    public static <T> T convertStringTo(String str, Class<T> t) {
        if (ObjectUtils.isEmpty(str)) return null;

        try {
            return new ObjectMapper().readValue(str, t);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> T convertStringTo(String str, TypeReference<T> t) {
        if (ObjectUtils.isEmpty(str)) return null;

        try {
            return new ObjectMapper().readValue(str, t);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public String convertToObjectToJson(Object object) throws JsonProcessingException {
        if ( object == null ) {
            return null;
        }

        return new ObjectMapper().writeValueAsString(object);
    }
}
