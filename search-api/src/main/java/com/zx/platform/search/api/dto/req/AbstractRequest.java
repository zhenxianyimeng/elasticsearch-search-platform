package com.zx.platform.search.api.dto.req;

import lombok.*;

import java.io.Serializable;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-06
 * @time: 19:52
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AbstractRequest implements Serializable {

    private static final long serialVersionUID = -907808523528362200L;

    protected String requestId;

    protected String appKey;

    protected Integer start;

    protected Integer rows;

}
