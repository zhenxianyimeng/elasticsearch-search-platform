package com.zx.platform.search.core.service;

import com.zx.platform.search.api.IQueryService;
import com.zx.platform.search.api.dto.req.FilterReqDTO;
import com.zx.platform.search.api.dto.req.QueryReqDTO;
import com.zx.platform.search.api.dto.resp.HitsRespDTO;
import com.zx.platform.search.api.exception.SearchException;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-29
 * @time: 20:08
 */
@Service
public class QueryServiceImpl implements IQueryService {
    @Override
    public HitsRespDTO query(QueryReqDTO reqDTO) throws SearchException {
        return null;
    }

    @Override
    public HitsRespDTO filter(FilterReqDTO reqDto) throws SearchException {
        return null;
    }
}
