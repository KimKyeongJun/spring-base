package com.kkj.base.common.utils;

import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjectUtil {

    private ObjectUtil() {
        throw new AssertionError();
    }
    public static <K, V> HashMap<K, V> newMapInstance(Class<K> keyType, Class<V> valueType){
        return new HashMap<K, V>();
    }

    public static <T> ArrayList<T> newListInstance(Class<T> type){
        return new ArrayList<T>();
    }

    public static <K, V> LinkedMultiValueMap<K, V> newMultiValueMapInstance(Class<K> keyType, Class<V> valueType){
        return new LinkedMultiValueMap<K, V>();
    }
}
