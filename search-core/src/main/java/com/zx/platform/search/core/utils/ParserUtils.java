package com.zx.platform.search.core.utils;

import org.elasticsearch.search.SearchHit;

import java.util.Collection;
import java.util.Map;

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
}
