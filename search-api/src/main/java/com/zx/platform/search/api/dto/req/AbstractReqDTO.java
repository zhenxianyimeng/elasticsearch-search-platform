package com.zx.platform.search.api.dto.req;

import lombok.*;

import java.io.Serializable;
import java.util.List;

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
public class AbstractReqDTO implements Serializable {

    private static final long serialVersionUID = -907808523528362200L;

    protected String project;

    protected String index;

    protected String alias;

    protected String requestId;

    protected String appKey;

    protected Integer start;

    protected Integer rows;

    protected List<String> includes;

    protected List<String> excludes;

}
