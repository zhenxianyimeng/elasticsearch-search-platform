package com.zx.platform.search.api.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-20
 * @time: 19:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoolQuery implements Serializable {
    private static final long serialVersionUID = -937571288814484512L;

    private List<FieldFilter> filterFields;
    private List<FieldFilter> mustFields;
    private List<FieldFilter> mustNotFields;
    private List<FieldFilter> shouldFields;
    private Integer minimumShouldMatch = 1;

}
