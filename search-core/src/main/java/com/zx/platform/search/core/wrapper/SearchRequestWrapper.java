package com.zx.platform.search.core.wrapper;

import com.zx.platform.search.api.dto.common.BoolQuery;
import com.zx.platform.search.api.dto.req.AbstractReqDTO;
import com.zx.platform.search.api.dto.req.FilterReqDTO;
import com.zx.platform.search.api.dto.req.QueryReqDTO;
import com.zx.platform.search.core.utils.SearchBuilderUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-11-30
 * @time: 21:46
 */
@Component
public class SearchRequestWrapper {

    public SearchRequest buildQueryRequest(QueryReqDTO reqDTO){
        SearchRequest searchRequest = this.buildFilterRequest(reqDTO);
        this.queryWrapper(searchRequest, reqDTO);
        return searchRequest;
    }

    public SearchRequest buildFilterRequest(FilterReqDTO reqDTO) {
        SearchRequest searchRequest = new SearchRequest(reqDTO.getIndex());
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        this.searchSourceWrapper(sourceBuilder, reqDTO);
        BoolQueryBuilder boolQueryBuilder = SearchBuilderUtils.boolQueryBuilder(
                new BoolQuery(reqDTO.getFilterFields(), reqDTO.getMustFields(), reqDTO.getMustNotFields(),
                        reqDTO.getShouldFields(), reqDTO.getMinNumShouldMatch())
        );

        sourceBuilder.query(boolQueryBuilder);
        searchRequest.source(sourceBuilder);

        return searchRequest;
    }

    private void queryWrapper(SearchRequest searchRequest, QueryReqDTO reqDTO){
        BoolQueryBuilder boolQueryBuilder = (BoolQueryBuilder) searchRequest.source().query();
        if(StringUtils.isEmpty(reqDTO.getQuery()) || CollectionUtils.isEmpty(reqDTO.getFieldBoostList())){
            return;
        }
        MultiMatchQueryBuilder multiMatchQueryBuilder = SearchBuilderUtils.multiMatchQueryBuilder(reqDTO.getQuery(), reqDTO.getFieldBoostList(),
                reqDTO.getSearchAnalyzer(), reqDTO.getOperator());
        if(reqDTO.getMust()){
            boolQueryBuilder.must(multiMatchQueryBuilder);
        }else {
            boolQueryBuilder.should(multiMatchQueryBuilder);
            boolQueryBuilder.minimumShouldMatch(reqDTO.getMinNumShouldMatch());
        }
    }

    private void searchSourceWrapper(SearchSourceBuilder sourceBuilder, FilterReqDTO filterReqDTO) {
        this.sourceCommonWrapper(sourceBuilder, filterReqDTO);
        this.sourceFetchWrapper(sourceBuilder, filterReqDTO);
    }

    private void sourceFetchWrapper(SearchSourceBuilder sourceBuilder, AbstractReqDTO reqDTO) {
        sourceBuilder.fetchSource(
                reqDTO.getIncludes() == null ? null : reqDTO.getIncludes().toArray(new String[reqDTO.getIncludes().size()]),
                reqDTO.getExcludes() == null ? null : reqDTO.getExcludes().toArray(new String[reqDTO.getExcludes().size()])
        );
    }

    private void sourceCommonWrapper(SearchSourceBuilder sourceBuilder, FilterReqDTO reqDTO) {
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
