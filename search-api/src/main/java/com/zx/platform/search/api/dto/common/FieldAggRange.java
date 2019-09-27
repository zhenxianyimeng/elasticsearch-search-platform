package com.zx.platform.search.api.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-20
 * @time: 20:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldAggRange implements Serializable {
    private static final long serialVersionUID = -1973508406702886642L;

    private String key;

    private Double from;

    private Double to;

    public FieldAggRange(Double from, Double to) {
        this.from = from;
        this.to = to;
    }
}
