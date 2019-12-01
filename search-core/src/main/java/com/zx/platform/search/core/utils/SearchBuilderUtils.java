package com.zx.platform.search.core.utils;

import com.zx.platform.search.api.constants.QueryOperatorEnum;
import com.zx.platform.search.api.dto.common.*;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregator;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-11-21
 * @time: 20:32
 */
public class SearchBuilderUtils {

    public static BoolQueryBuilder boolQueryBuilder(BoolQuery boolQuery) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        if (!CollectionUtils.isEmpty(boolQuery.getFilterFields())) {
            boolQuery.getFilterFields().forEach(filter -> boolQueryBuilder.filter(parseFilter(filter)));
        }

        if (!CollectionUtils.isEmpty(boolQuery.getMustFields())) {
            boolQuery.getMustFields().forEach(filter -> boolQueryBuilder.must(parseFilter(filter)));
        }

        if (!CollectionUtils.isEmpty(boolQuery.getMustNotFields())) {
            boolQuery.getMustNotFields().forEach(filter -> boolQueryBuilder.mustNot(parseFilter(filter)));
        }

        if (!CollectionUtils.isEmpty(boolQuery.getShouldFields())) {
            boolQuery.getShouldFields().forEach(filter -> boolQueryBuilder.should(parseFilter(filter)));
            boolQueryBuilder.minimumShouldMatch(boolQuery.getMinimumShouldMatch());
        }

        return boolQueryBuilder;
    }

    private static AbstractQueryBuilder parseFilter(FieldFilter filter) {
        AbstractQueryBuilder queryBuilder = null;
        switch (filter.getType()) {
            case TERM:
                queryBuilder = QueryBuilders.termQuery(filter.getField(), filter.getValue());
                break;
            case TERMS:
                queryBuilder = QueryBuilders.termsQuery(filter.getField(), ParserUtils.parseFieldTerms(filter.getValue()));
                break;
            case EXISTS:
                queryBuilder = QueryBuilders.existsQuery(filter.getField());
                break;
            default:
                break;
        }
        queryBuilder.boost(filter.getBoost());
        return queryBuilder;
    }

    public static MultiMatchQueryBuilder multiMatchQueryBuilder(String query, List<FieldBoost> boostList, String analyzer, QueryOperatorEnum operator) {
        String[] fields = new String[boostList.size()];
        for (int i = 0; i < boostList.size(); i++) {
            fields[i] = boostList.get(i).getField();
        }
        MultiMatchQueryBuilder multiMatchQueryBuilder = new MultiMatchQueryBuilder(query, fields);
        multiMatchQueryBuilder.analyzer(analyzer);
        for (FieldBoost fieldBoost : boostList) {
            multiMatchQueryBuilder.field(fieldBoost.getField(), fieldBoost.getBoost());
        }
        if (operator == QueryOperatorEnum.OR) {
            multiMatchQueryBuilder.operator(Operator.OR);
        } else {
            multiMatchQueryBuilder.operator(Operator.AND);
        }
        return multiMatchQueryBuilder;
    }

    public static AggregationBuilder aggBuilder(FieldAgg fieldAgg) {
        AggregationBuilder builder = null;
        switch (fieldAgg.getType()) {
            case COUNT:
                builder = AggregationBuilders.count(fieldAgg.getKey()).field(fieldAgg.getField());
                break;
            case AVG:
                builder = AggregationBuilders.avg(fieldAgg.getKey()).field(fieldAgg.getField());
                break;
            case MAX:
                builder = AggregationBuilders.max(fieldAgg.getKey()).field(fieldAgg.getField());
                break;
            case MIN:
                builder = AggregationBuilders.min(fieldAgg.getKey()).field(fieldAgg.getField());
                break;
            case SUM:
                builder = AggregationBuilders.sum(fieldAgg.getKey()).field(fieldAgg.getField());
                break;
            case RANGE:
                RangeAggregationBuilder rangeBuilder = AggregationBuilders
                        .range(fieldAgg.getKey()).field(fieldAgg.getField());
                List<FieldAggRange> fieldAggRanges = ParserUtils.parseFieldAggRange(fieldAgg.getValue());
                for (FieldAggRange aggRange : fieldAggRanges) {
                    RangeAggregator.Range range = new RangeAggregator.Range(aggRange.getKey(), aggRange.getFrom(), aggRange.getTo());
//                    rangeBuilder.addRange(aggRange.getKey(), aggRange.getFrom(), aggRange.getTo());
                    rangeBuilder.addRange(range);
                }
                builder = rangeBuilder;
                break;
            case TERMS:
                builder = AggregationBuilders.terms(fieldAgg.getKey()).field(fieldAgg.getField())
                        .size(fieldAgg.getSize() == null ? 20 : fieldAgg.getSize());
                break;
            default:
                break;
        }
        return builder;
    }
}
