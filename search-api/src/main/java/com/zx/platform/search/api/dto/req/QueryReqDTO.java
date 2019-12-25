package com.zx.platform.search.api.dto.req;

import com.zx.platform.search.api.constants.QueryOperatorEnum;
import com.zx.platform.search.api.dto.common.FieldAgg;
import com.zx.platform.search.api.dto.common.FieldBoost;
import com.zx.platform.search.api.dto.common.FieldFilter;
import com.zx.platform.search.api.dto.common.FieldSort;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-09
 * @time: 20:40
 */
@Data
public class QueryReqDTO{

    private static final long serialVersionUID = -1784691063533819232L;

    private List<FieldFilter> filterFields;

    private List<FieldFilter> mustFields;

    private List<FieldFilter> mustNotFields;

    private List<FieldFilter> shouldFields;

    private Integer minNumShouldMatch = 1;

    private List<FieldSort> sortFields;

    private String query;

    private List<FieldBoost> fieldBoostList;

    private String searchAnalyzer = "ik_smart";

    private QueryOperatorEnum operator = QueryOperatorEnum.OR;

    private Boolean must = false;

    private List<FieldAgg> aggList;

}
