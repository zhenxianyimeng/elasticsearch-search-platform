package com.zx.platform.search.api.dto.common;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-09
 * @time: 20:43
 */
@Data
public class Boost implements Serializable {

    private static final long serialVersionUID = -8421165501823935827L;

    private String field;

    private Float boost;

}
