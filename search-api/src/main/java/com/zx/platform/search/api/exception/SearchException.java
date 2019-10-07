package com.zx.platform.search.api.exception;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-26
 * @time: 23:23
 */
public class SearchException extends Exception {
    private static final long serialVersionUID = 2496783353941610796L;

    private SearchExceptionCodeEnum codeEnum;

    public SearchException(SearchExceptionCodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public SearchExceptionCodeEnum getCodeEnum() {
        return codeEnum;
    }

    public void setCodeEnum(SearchExceptionCodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }

    @Override
    public String getMessage() {
        return codeEnum.toString();
    }
}
