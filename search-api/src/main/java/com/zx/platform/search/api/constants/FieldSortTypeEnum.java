package com.zx.platform.search.api.constants;

import lombok.AllArgsConstructor;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-06
 * @time: 19:43
 */
@AllArgsConstructor
public enum FieldSortTypeEnum {
    ASC(1, "asc"),
    DESC(2, "desc");

    private final int id;
    private final String value;
}
