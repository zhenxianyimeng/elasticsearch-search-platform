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
    AND("and"),
    OR("or");

    private String msg;

}
