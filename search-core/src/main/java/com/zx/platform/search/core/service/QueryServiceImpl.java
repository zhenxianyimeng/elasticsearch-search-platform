package com.zx.platform.search.core.service;

import com.zx.platform.search.api.api.IQueryService;
import com.zx.platform.search.api.dto.req.FilterReqDTO;
import com.zx.platform.search.api.dto.req.QueryReqDTO;
import com.zx.platform.search.api.dto.resp.HitsRespDTO;
import com.zx.platform.search.api.exception.SearchException;
import com.zx.platform.search.core.utils.ParserUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class QueryServiceImpl extends BaseSearchService implements IQueryService {

    @Autowired
    private RestHighLevelClient client;

    private static final Logger logger = LoggerFactory.getLogger(QueryServiceImpl.class);

    @Override
    public HitsRespDTO query(QueryReqDTO reqDTO) throws SearchException {

        return null;
    }

    @Override
    public HitsRespDTO filter(FilterReqDTO reqDTO) throws SearchException {
        HitsRespDTO hitsRespDTO = new HitsRespDTO();
        long start = System.currentTimeMillis();
        try {
            SearchRequest searchRequest = super.builderSearchRequest(reqDTO);
            List<Object> hits = new ArrayList<>();
            logger.info("es {}, dsl={}", "filter", searchRequest.source());
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if(searchResponse == null || !RestStatus.OK.equals(searchResponse.status())){
                logger.warn("es {} fail req={}", "flter", reqDTO);
            }else {
                hitsRespDTO.setTotal(searchResponse.getHits().getTotalHits());
                for(SearchHit sh : searchResponse.getHits()){
                    hits.add(ParserUtils.hitToMap(sh));
                }
                hitsRespDTO.setHits(hits);
            }
        }catch (Exception e){
            logger.error("search error");
        }finally {
            long cost = System.currentTimeMillis() - start;
            logger.info("search {} cost={}ms", "filter", cost);
        }
        return hitsRespDTO;
    }
}
