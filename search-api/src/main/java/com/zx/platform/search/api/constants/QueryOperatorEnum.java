package com.zx.platform.search.api.constants;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-09
 * @time: 20:47
 */
@AllArgsConstructor
public enum QueryOperatorEnum {
    /**
     * 分词后的命中情况，and表示所有分词结果命中才返回，or表示其中一个分词命中就返回
     */
    AND("and"),
    OR("or");

    private String msg;

}
