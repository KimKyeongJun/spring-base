package com.kkj.base.web.dto;

import lombok.Data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel
public class SearchRequestDTO {

    @ApiModelProperty(value = "페이징 처리 정보", example = "[0,9]")
    private String range;

    @ApiModelProperty(value = "정렬 정보", example = "[\"id\",\"ASC\"]")
    private String sort;

    @ApiModelProperty(value = "필터 정보", example = "{\"name\":\"aa\"}")
    private String filter;

}
