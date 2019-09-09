package com.zx.platform.search.api.constants;

import lombok.AllArgsConstructor;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-09
 * @time: 20:35
 */
@AllArgsConstructor
public enum CrudTypeEnum {
    GET("get"),
    ADD("add"),
    UPDATE("update"),
    UPSERT("upsert"),
    DELETE("delete");
    private String msg;
}
