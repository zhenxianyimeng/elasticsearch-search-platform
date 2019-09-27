package com.zx.platform.search.api.dto.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-26
 * @time: 23:10
 */
@Data
public class HitsRespDTO<T> implements Serializable {
    private static final long serialVersionUID = 5357201255885836936L;

    protected Long total;

    protected Float maxScore;

    protected List<T> hits;

}
