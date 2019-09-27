package com.zx.platform.search.api.dto.req;

import com.zx.platform.search.api.dto.common.FieldAgg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-20
 * @time: 20:05
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AggReqDTO extends QueryReqDTO{
    private static final long serialVersionUID = 7895440411439609619L;

    private List<FieldAgg> aggList;

}
