package com.zx.platform.search.core.service;

import com.zx.platform.search.api.api.ICrudService;
import com.zx.platform.search.api.dto.common.BoolQuery;
import com.zx.platform.search.api.dto.req.AbstractReqDTO;
import com.zx.platform.search.api.dto.req.FilterReqDTO;
import com.zx.platform.search.api.dto.resp.HitsRespDTO;
import com.zx.platform.search.api.exception.SearchException;
import com.zx.platform.search.core.utils.ParserUtils;
import com.zx.platform.search.core.utils.SearchBuilderUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-11-21
 * @time: 20:05
 */
public class BaseSearchService {

    public void searchSourceWrapper(SearchSourceBuilder sourceBuilder, FilterReqDTO filterReqDTO) {
        this.sourceCommonWrapper(sourceBuilder, filterReqDTO);
        this.sourceFetchWrapper(sourceBuilder, filterReqDTO);
    }

    public SearchRequest builderSearchRequest(FilterReqDTO reqDTO) {
        HitsRespDTO hitsRespDTO = new HitsRespDTO();
        SearchRequest searchRequest = new SearchRequest(reqDTO.getIndex());
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        this.searchSourceWrapper(sourceBuilder, reqDTO);
        BoolQueryBuilder boolQueryBuilder = SearchBuilderUtils.boolQueryBuilder(
                new BoolQuery(reqDTO.getFilterFields(), reqDTO.getMustFields(), reqDTO.getMustNotFields(),
                        reqDTO.getShouldFields(), reqDTO.getMinNumShouldMatch())
        );
        //subBuilder
        subBuilder(sourceBuilder, boolQueryBuilder, reqDTO);

        sourceBuilder.query(boolQueryBuilder);
        searchRequest.source(sourceBuilder);

        return searchRequest;
    }

    public void subBuilder(SearchSourceBuilder sourceBuilder, BoolQueryBuilder boolQueryBuilder, FilterReqDTO filterReqDTO) {

    }

    public void sourceFetchWrapper(SearchSourceBuilder sourceBuilder, AbstractReqDTO reqDTO) {
        sourceBuilder.fetchSource(
                reqDTO.getIncludes() == null ? null : reqDTO.getIncludes().toArray(new String[reqDTO.getIncludes().size()]),
                reqDTO.getExcludes() == null ? null : reqDTO.getExcludes().toArray(new String[reqDTO.getExcludes().size()])
        );
    }

    public void sourceCommonWrapper(SearchSourceBuilder sourceBuilder, FilterReqDTO reqDTO) {
        if (reqDTO.getFrom() != null && reqDTO.getFrom() > 0) {
            sourceBuilder.from(reqDTO.getFrom());
        }
        sourceBuilder.size(reqDTO.getSize());

        if (!CollectionUtils.isEmpty(reqDTO.getSortFields())) {
            reqDTO.getSortFields().forEach(fieldSort ->
                    sourceBuilder.sort(fieldSort.getField(), SortOrder.fromString(fieldSort.getOrder().getValue())));
        }
    }
}
