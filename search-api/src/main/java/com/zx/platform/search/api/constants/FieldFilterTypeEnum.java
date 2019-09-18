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
    TERM("term"),
    TERMS("terms"),
    EXISTS("exists");

    private final String value;

}
