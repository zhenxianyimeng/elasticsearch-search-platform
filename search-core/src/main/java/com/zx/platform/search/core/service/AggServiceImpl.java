package com.zx.platform.search.core.service;

import com.zx.platform.search.api.api.IAggService;
import com.zx.platform.search.api.dto.common.FieldAgg;
import com.zx.platform.search.api.dto.req.AggReqDTO;
import com.zx.platform.search.api.dto.resp.AggRespDTO;
import com.zx.platform.search.api.exception.SearchException;
import com.zx.platform.search.api.exception.SearchExceptionCodeEnum;
import com.zx.platform.search.core.utils.ParserUtils;
import com.zx.platform.search.core.wrapper.SearchRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-29
 * @time: 20:06
 */
@Service
@Slf4j
public class AggServiceImpl implements IAggService {

    @Autowired
    private SearchRequestWrapper requestWrapper;

    @Autowired
    private RestHighLevelClient client;

    @Override
    public AggRespDTO agg(AggReqDTO reqDTO) throws SearchException {
        AggRespDTO aggRespDTO = new AggRespDTO();
        long start = System.currentTimeMillis();
        try {
            SearchRequest searchRequest = requestWrapper.buildAggRequest(reqDTO);
            List<Object> hits = new ArrayList<>();
            log.info("es agg, dsl={}", searchRequest.source());
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if(searchResponse == null || !RestStatus.OK.equals(searchResponse.status())){
                log.warn("es agg fail req={}",reqDTO);
            }else {
                aggRespDTO.setTotal(searchResponse.getHits().getTotalHits());
                for (SearchHit sh : searchResponse.getHits()) {
                    hits.add(ParserUtils.hitToMap(sh));
                }
                aggRespDTO.setAggData(ParserUtils.parseAggs(reqDTO.getAggList(), searchResponse.getAggregations()));
            }

        }catch (Exception e){
            log.error("es agg error",e);
            throw new SearchException(SearchExceptionCodeEnum.INNER_ERROR);
        }finally {
            long cost = System.currentTimeMillis() - start;
            log.info("es agg requestId={}|cost={}ms", reqDTO.getRequestId(), cost);
        }
        return aggRespDTO;
    }





}
