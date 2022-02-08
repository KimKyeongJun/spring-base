package com.kkj.base.common.utils;

import java.util.Map;
import java.util.Arrays;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import com.kkj.base.web.dto.SearchRequestVO;
import com.kkj.base.web.dto.SearchRequestDTO;


public class PaginationUtil {

    public static SearchRequestVO parse(SearchRequestDTO searchRequestDTO) {
        SearchRequestVO searchRequestVO = new SearchRequestVO();

        if (searchRequestDTO != null) {
            String range = searchRequestDTO.getRange();
            searchRequestVO.setPageable(!ObjectUtils.isEmpty(range));

            if (searchRequestVO.isPageable()) {
                int[] rangeArr = getRange(range);

                searchRequestVO.setOffset(rangeArr[0]);
                searchRequestVO.setLimit(rangeArr[1] - rangeArr[0] + 1);
            }

            String sort = searchRequestDTO.getSort();
            searchRequestVO.setSortable(!ObjectUtils.isEmpty(sort));
            if (searchRequestVO.isSortable()) {
                String[] sortArr = convertStringToArray(sort);

                searchRequestVO.setOrder(sortArr[0]);
                searchRequestVO.setSort(sortArr[1]);
            }

            String filter = searchRequestDTO.getFilter();
            if (!ObjectUtils.isEmpty(filter)) {
                searchRequestVO.setSearch(getSearch(filter));
            }
        }
        return searchRequestVO;
    }

    private static int[] getRange(String range) {
        return Arrays.stream(convertStringToArray(range))
                .map(String::trim).mapToInt(Integer::parseInt).toArray();
    }

    private static String[] convertStringToArray(String sort) {
        return sort.substring(1, sort.length() - 1).replaceAll("\"", "").split(",");
    }

    private static Map<String, Object> getSearch(String filter) {
        try {
            return new ObjectMapper().readValue(filter, new TypeReference<Map<String, Object>>() {});
        } catch(Exception e) {
            return null;
        }
    }
}
