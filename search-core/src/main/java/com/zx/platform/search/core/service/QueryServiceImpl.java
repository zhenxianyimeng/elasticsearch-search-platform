package com.zx.platform.search.core.service;

import com.zx.platform.search.api.api.IQueryService;
import com.zx.platform.search.api.dto.req.QueryReqDTO;
import com.zx.platform.search.api.dto.resp.HitsRespDTO;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-29
 * @time: 20:08
 */
@Service
@Slf4j
public class QueryServiceImpl implements IQueryService {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private SearchRequestWrapper requestWrapper;

    @Override
    public HitsRespDTO query(QueryReqDTO reqDTO) throws SearchException {
        HitsRespDTO hitsRespDTO = new HitsRespDTO();
        long start = System.currentTimeMillis();
        try {
            SearchRequest searchRequest = requestWrapper.buildQueryRequest(reqDTO);
            List<Object> hits = new ArrayList<>();
            log.info("es query, dsl={}", searchRequest.source());
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if(searchResponse == null || !RestStatus.OK.equals(searchResponse.status())){
                log.warn("es query fail req={}",reqDTO);
            }else {
                hitsRespDTO.setTotal(searchResponse.getHits().getTotalHits());
                for(SearchHit sh : searchResponse.getHits()){
                    hits.add(ParserUtils.hitToMap(sh));
                }
                hitsRespDTO.setHits(hits);
                hitsRespDTO.setAggData(ParserUtils.parseAggs(reqDTO.getAggList(), searchResponse.getAggregations()));
            }
        }catch (Exception e){
            log.error("es query error",e);
            throw new SearchException(SearchExceptionCodeEnum.INNER_ERROR);
        }finally {
            long cost = System.currentTimeMillis() - start;
            log.info("es query requestId={}|cost={}ms", reqDTO.getRequestId(), cost);
        }
        return hitsRespDTO;
    }
}
