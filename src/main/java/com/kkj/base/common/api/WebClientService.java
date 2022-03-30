package com.kkj.base.common.api;

import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public interface WebClientService {
    <T> T requestGetMethod(String url, @Nullable Map<String, String> headersMap, @Nullable MultiValueMap<String, String> paramsMap, Class<T> responseClazz);

    <T> T requestGetMethodExchange(String url, @Nullable Map<String, String> headersMap, @Nullable MultiValueMap<String, String> paramsMap,
                                          Class<T> responseClazz, @Nullable Map<String, String> responseHeaders);

    <T> T requestPostMethod(String url, @Nullable Map<String, String> headersMap, Object requestBody, Class<T> responseClazz);

    <T> T requestPutMethod(String url, @Nullable Map<String, String> headersMap, Object requestBody, Class<T> responseClazz);

    <T> T requestDeleteMethod(String url, @Nullable Map<String, String> headersMap, Class<T> responseClazz);
}
