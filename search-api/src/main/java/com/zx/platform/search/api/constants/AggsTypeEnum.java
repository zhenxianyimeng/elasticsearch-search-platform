package com.zx.platform.search.api.constants;

import lombok.AllArgsConstructor;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-06
 * @time: 19:38
 */
@AllArgsConstructor
public enum AggsTypeEnum {
    COUNT(1, "metrics", "count"),
    SUM(2, "metrics", "sum"),
    MAX(3, "metrics", "max"),
    MIN(4, "metrics", "min"),
    AVG(5, "metrics", "avg"),
    TERMS(101, "bucket", "terms"),
    RANGE(102, "bucket", "range");

    private final int id;
    private final String type;
    private final String value;

}
