package com.zx.platform.search.api.dto.req;

import com.zx.platform.search.api.constants.QueryOperatorEnum;
import com.zx.platform.search.api.dto.common.FieldBoost;
import lombok.Data;

import java.util.List;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-09
 * @time: 20:40
 */
@Data
public class QueryReqDTO extends FilterReqDTO{

    private static final long serialVersionUID = -1784691063533819232L;

    protected String query;

    protected List<FieldBoost> fieldBoostList;

    protected String searchAnalyzer = "ik_smart";

    protected QueryOperatorEnum operator = QueryOperatorEnum.OR;

    protected Boolean must = false;

}
