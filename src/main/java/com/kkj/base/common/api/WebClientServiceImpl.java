package com.kkj.base.common.api;

import com.kkj.base.common.utils.ObjectUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service("WebClientService")
@Slf4j
@RequiredArgsConstructor
public class WebClientServiceImpl implements WebClientService {

    private final WebClient webClient;

    public <T> T requestGetMethod(String url, @Nullable Map<String, String> headersMap, @Nullable MultiValueMap<String, String> paramsMap, Class<T> responseClazz) {
        T result = webClient.mutate()
                .baseUrl(url)
                .build()
                .get()
                .uri(UriBuilder ->
                        UriBuilder
                                .queryParams(paramsMap != null ? paramsMap : ObjectUtil.newMultiValueMapInstance(String.class, String.class))
                                .build())
                .headers(headers -> {
                    headers.setAll(headersMap != null ? headersMap : ObjectUtil.newMapInstance(String.class, String.class));
                })
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    log.error("reuqest URL : {}", url);
                    return Mono.error(new IllegalStateException(response.statusCode().toString()));
                })
                .bodyToMono(responseClazz)
                .flux()
                .toStream()
                .findFirst()
                .orElse(null);
        //.block();		//block I/O
        return result;
    }

    public <T> T requestGetMethodExchange(String url, @Nullable Map<String, String> headersMap, @Nullable MultiValueMap<String, String> paramsMap,
                                          Class<T> responseClazz, @Nullable Map<String, String> responseHeaders) {
        Mono<T> result = webClient.mutate()
                .baseUrl(url)
                .build()
                .get()
                .uri(UriBuilder ->
                        UriBuilder
                                .queryParams(paramsMap != null ? paramsMap : ObjectUtil.newMultiValueMapInstance(String.class, String.class))
                                .build())
                //.accept(MediaType.APPLICATION_JSON)
                .headers(headers -> {
                    headers.setAll(headersMap != null ? headersMap : ObjectUtil.newMapInstance(String.class, String.class));
                })
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().isError()) {
                        log.error("reuqest URL : {}, url");
                        return Mono.error(new IllegalStateException());
                    }
                    responseHeaders.putAll(clientResponse.headers().asHttpHeaders().toSingleValueMap());
                    log.debug(clientResponse.headers().asHttpHeaders().toString());
                    return clientResponse.bodyToMono(responseClazz);
                });

        return result.flux()
                .toStream()
                .findFirst()
                .orElse(null);

    }

    public <T> T requestPostMethod(String url, @Nullable Map<String, String> headersMap, Object requestBody, Class<T> responseClazz) {
        T result = webClient.mutate()
                .baseUrl(url)
                .build()
                .post()
                .headers(headers -> {
                    headers.setAll(headersMap != null ? headersMap : ObjectUtil.newMapInstance(String.class, String.class));
                })
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    log.error("reuqest URL : {}, url");
                    return Mono.error(new IllegalStateException());

                })
                .bodyToMono(responseClazz)
                .flux()
                .toStream()
                .findFirst()
                .orElse(null);
        return result;
    }

    public <T> T requestPutMethod(String url, @Nullable Map<String, String> headersMap, Object requestBody, Class<T> responseClazz) {
        T result = webClient.mutate()
                .baseUrl(url)
                .build()
                .put()
                .headers(headers -> {
                    headers.setAll(headersMap != null ? headersMap : ObjectUtil.newMapInstance(String.class, String.class));
                })
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    log.error("reuqest URL : {}, url");
                    return Mono.error(new IllegalStateException());

                })
                .bodyToMono(responseClazz)
                .flux()
                .toStream()
                .findFirst()
                .orElse(null);
        return result;
    }

    public <T> T requestDeleteMethod(String url, @Nullable Map<String, String> headersMap, Class<T> responseClazz) {
        T result = webClient.mutate()
                .baseUrl(url)
                .build()
                .delete()
                .headers(headers -> {
                    headers.setAll(headersMap != null ? headersMap : ObjectUtil.newMapInstance(String.class, String.class));
                })
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    log.error("reuqest URL : {}, url");
                    return Mono.error(new IllegalStateException());

                })
                .bodyToMono(responseClazz)
                .flux()
                .toStream()
                .findFirst()
                .orElse(null);
        return result;
    }

}
