package com.zx.platform.search.api.dto.req;

import com.zx.platform.search.api.dto.common.FieldFilter;
import com.zx.platform.search.api.dto.common.FieldSort;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-09
 * @time: 20:26
 */
@Data
public class FilterReqDTO extends AbstractReqDTO implements Serializable {

    private static final long serialVersionUID = -2691212494530466957L;

    protected List<FieldFilter> filterFields;
    protected List<FieldFilter> mustFields;
    protected List<FieldFilter> mustNotFields;
    protected List<FieldFilter> shouldFields;
    protected Integer minNumShouldMatch = 1;
    protected List<FieldSort> sortFields;

}
