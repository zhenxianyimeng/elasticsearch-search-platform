package com.zx.platform.search.api.dto.common;

import com.zx.platform.search.api.constants.FieldSortTypeEnum;
import lombok.*;

import java.io.Serializable;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-06
 * @time: 20:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldSort implements Serializable {
    private static final long serialVersionUID = -4235448628111446039L;

    private String field;

    private FieldSortTypeEnum order;

}
