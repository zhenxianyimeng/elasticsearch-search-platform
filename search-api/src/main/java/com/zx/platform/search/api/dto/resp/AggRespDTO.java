package com.zx.platform.search.api.dto.resp;

import lombok.Data;

import java.util.Map;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-26
 * @time: 23:17
 */
@Data
public class AggRespDTO extends HitsRespDTO {
    private static final long serialVersionUID = -8052919885060957949L;

    private Map<String, Object> aggData;

}
