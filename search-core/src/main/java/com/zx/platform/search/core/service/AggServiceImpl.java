package com.zx.platform.search.core.service;

import com.zx.platform.search.api.IAggService;
import com.zx.platform.search.api.dto.req.AggReqDTO;
import com.zx.platform.search.api.dto.resp.AggRespDTO;
import com.zx.platform.search.api.exception.SearchException;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-29
 * @time: 20:06
 */
@Service
public class AggServiceImpl implements IAggService {
    @Override
    public AggRespDTO agg(AggReqDTO reqDTO) throws SearchException {
        return null;
    }
}
