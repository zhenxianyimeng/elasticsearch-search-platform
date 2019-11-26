package com.zx.platform.search.api.dto.common;

import lombok.*;

import java.io.Serializable;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-06
 * @time: 19:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldRange<T> implements Serializable {
    private static final long serialVersionUID = -5180170114929718707L;

    private T from;
    private T to;
    private Boolean includeLower = true;
    private Boolean includeUpper = false;
}
