package com.zx.platform.search.api.dto.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-26
 * @time: 23:08
 */
@Data
public class CrudRespDTO<T> implements Serializable {
    private static final long serialVersionUID = 5529250553852765028L;
    private T content;
}
