package com.zx.platform.search.core.utils;

import com.zx.platform.search.api.dto.common.BoolQuery;
import com.zx.platform.search.api.dto.common.FieldFilter;
import org.elasticsearch.index.query.AbstractQueryBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.util.CollectionUtils;

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
}
