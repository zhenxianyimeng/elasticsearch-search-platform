package com.zx.platform.search.api.api;

import com.zx.platform.search.api.dto.req.QueryReqDTO;
import com.zx.platform.search.api.dto.resp.HitsRespDTO;
import com.zx.platform.search.api.exception.SearchException;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-29
 * @time: 20:01
 */
public interface IQueryService {
    HitsRespDTO query(QueryReqDTO reqDTO) throws SearchException;
}
