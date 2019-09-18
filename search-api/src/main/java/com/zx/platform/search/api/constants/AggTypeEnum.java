package com.zx.platform.search.api.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-06
 * @time: 19:38
 */
@AllArgsConstructor
@Getter
public enum AggTypeEnum {
    COUNT("metrics", "count"),
    SUM("metrics", "sum"),
    MAX("metrics", "max"),
    MIN("metrics", "min"),
    AVG("metrics", "avg"),
    TERMS("bucket", "terms"),
    RANGE("bucket", "range");

    private final String type;
    private final String value;

}
