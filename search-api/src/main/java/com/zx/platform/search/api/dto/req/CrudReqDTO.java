package com.zx.platform.search.api.dto.req;

import com.zx.platform.search.api.constants.CrudTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-09
 * @time: 20:31
 */
@Data
public class CrudReqDTO<T> implements Serializable {

    private static final long serialVersionUID = -8945771667117188107L;

    private String id;

    private CrudTypeEnum action;

    private T data;
}
