package com.zx.platform.search.api.exception;

import lombok.AllArgsConstructor;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-29
 * @time: 20:14
 */
@AllArgsConstructor
public enum  SearchExceptionCodeEnum {
    INNER_ERROR(900001,"系统内部错误")
                        ;
    private Integer code;

    private String msg;
}
