package com.zx.platform.search.api.constants;

import lombok.AllArgsConstructor;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-06
 * @time: 19:42
 */
@AllArgsConstructor
public enum FieldFilterTypeEnum {
    TERM(1, "term"),
    TERMS(2, "terms"),
    EXISTS(3, "exists");

    private final int id;
    private final String value;

}
