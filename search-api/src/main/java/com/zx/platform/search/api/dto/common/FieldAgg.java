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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldAgg implements Serializable {
    private static final long serialVersionUID = -4640743531239838629L;

    private String key;
    private String field;
    private AggTypeEnum type;
    private List<FieldAgg> subAggList;
    private Integer size;
    private Object value;

    public FieldAgg(String key, String field, AggTypeEnum type) {
        this.key = key;
        this.field = field;
        this.type = type;
    }
}
