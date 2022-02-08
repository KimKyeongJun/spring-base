package com.kkj.base.common.utils.enummapper;


// DB에 저장되는 데이터를 관리하는 Enum에서 구현체를 전달 받아서 사용한다.
public interface EntityEnumerable {

    String getCode();

    String getValue();
}
