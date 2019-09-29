package com.zx.platform.search.api;

import com.zx.platform.search.api.dto.req.AggReqDTO;
import com.zx.platform.search.api.dto.resp.AggRespDTO;
import com.zx.platform.search.api.exception.SearchException;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-29
 * @time: 20:04
 */
public interface IAggService {
    AggRespDTO agg(AggReqDTO reqDTO) throws SearchException;
}
