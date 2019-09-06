package com.zx.platform.search.api.dto.resp;

import lombok.*;

import java.io.Serializable;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-06
 * @time: 20:11
 */
@NoArgsConstructor
@ToString
@Getter
@Setter
@AllArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -5696930514020607879L;

    private boolean success;

    private Integer code;

    private String msg;

    private String requestId;

    private T data;

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(boolean success, Integer code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> success(){
        return new Result<T>(true, 0, "Success");
    }

}
