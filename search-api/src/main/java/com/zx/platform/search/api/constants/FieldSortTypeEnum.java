package com.zx.platform.search.api.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-06
 * @time: 19:43
 */
@AllArgsConstructor
@Getter
public enum FieldSortTypeEnum {
    ASC("asc"),
    DESC("desc");

    private final String value;
}
