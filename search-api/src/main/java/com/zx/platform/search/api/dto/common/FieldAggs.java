package com.zx.platform.search.api.dto.common;

import com.zx.platform.search.api.constants.AggTypeEnum;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-06
 * @time: 19:56
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FieldAggs implements Serializable {
    private static final long serialVersionUID = -4640743531239838629L;

    private String key;
    private String field;
    private AggTypeEnum type;
    private List<FieldAggs> subAggs;

}
