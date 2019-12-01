package com.zx.platform.search.core.utils;

import com.zx.platform.search.api.dto.common.FieldAgg;
import com.zx.platform.search.api.dto.common.FieldAggRange;
import com.zx.platform.search.core.EsConstants;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.NumericMetricsAggregation;

import java.util.*;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-11-21
 * @time: 20:33
 */
public class ParserUtils {

    public static Collection parseFieldTerms(Object value){
        if(value == null){
            return null;
        }
        Collection fieldList = null;
        if(value instanceof Collection){
            fieldList = (Collection) value;
        }
        return fieldList;
    }

    public static Map<String, Object> hitToMap(SearchHit hit){
        Map<String, Object> map = hit.getSourceAsMap();

        return map;
    }

    public static List<FieldAggRange> parseFieldAggRange(Object value){
        List<FieldAggRange> list = new LinkedList<>();
        if(value instanceof List){
            list = (List<FieldAggRange>) value;
        }
        return list;
    }

    public static Map<String, Object> parseBucket(MultiBucketsAggregation.Bucket bucket, FieldAgg fieldAgg) {
        Map<String, Object> map = new HashMap<>();
        map.put(bucket.getKeyAsString(), bucket.getDocCount());
        return map;
    }

    public static Map<String, Object> parseAggs(List<FieldAgg> fieldAggList, Aggregations aggregations) {
        Map<String, Object> map = new HashMap<>();
        if (aggregations == null) {
            return map;
        }
        for (FieldAgg fieldAgg : fieldAggList) {
            Aggregation agg = aggregations.get(fieldAgg.getKey());
            if (EsConstants.AGG_TYPE_METRICS.equals(fieldAgg.getType().getType())) {
                if (agg instanceof NumericMetricsAggregation.SingleValue) {
                    NumericMetricsAggregation.SingleValue singleValue = (NumericMetricsAggregation.SingleValue) agg;
                    map.put(fieldAgg.getKey(), singleValue.value());
                }
            } else if (EsConstants.AGG_TYPE_BUCKET.equals(fieldAgg.getType().getType())) {
                List<Object> buckets = new LinkedList<>();
                switch (fieldAgg.getType()) {
                    case TERMS:
                        for (Terms.Bucket bucket : ((Terms) agg).getBuckets()) {
                            buckets.add(ParserUtils.parseBucket(bucket, fieldAgg));
                        }
                        break;
                    case RANGE:
                        for (Range.Bucket bucket : ((Range) agg).getBuckets()) {
                            buckets.add(ParserUtils.parseBucket(bucket, fieldAgg));
                        }
                    default:
                        break;
                }
                map.put(fieldAgg.getKey(), buckets);
            }
        }
        return map;
    }
}
