package com.kkj.base.web.dto;

import java.util.Map;

import lombok.Data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel
public class SearchRequestVO {

    @ApiModelProperty(value = "페이지 검색 시작 번호", example = "0")
    private int offset = 0;
    @ApiModelProperty(value = "검색 페이지 범위", example = "10")
    private int limit = 10;

    @ApiModelProperty(value = "정렬 필드", example = "name")
    private String order;
    @ApiModelProperty(value = "정렬 방향", example = "ASC or DESC")
    private String sort;

    @ApiModelProperty(value = "검색 정보", example = "{\"name\":\"aaa\"}")
    private Map<String, Object> search;

    private boolean isPageable;
    private boolean isSortable;
}
