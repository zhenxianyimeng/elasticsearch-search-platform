package com.zx.platform.search.api.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-09
 * @time: 20:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldBoost implements Serializable {

    private static final long serialVersionUID = -8421165501823935827L;

    private String field;

    private Float boost;

    public FieldBoost(String field) {
        this.field = field;
        this.boost = 1.0f;
    }
}
