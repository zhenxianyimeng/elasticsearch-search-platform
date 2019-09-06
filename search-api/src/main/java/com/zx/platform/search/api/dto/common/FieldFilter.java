package com.zx.platform.search.api.dto.common;

import com.zx.platform.search.api.constants.FieldFilterTypeEnum;
import lombok.*;

import java.io.Serializable;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-06
 * @time: 19:57
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FieldFilter<T> implements Serializable {
    private static final long serialVersionUID = -7326811972566747007L;

    private String field;
    private FieldFilterTypeEnum type;
    private T value;
}
