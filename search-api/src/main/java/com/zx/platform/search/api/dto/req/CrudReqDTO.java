package com.zx.platform.search.api.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-09
 * @time: 20:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrudReqDTO<T> extends AbstractReqDTO {

    private static final long serialVersionUID = -8945771667117188107L;

    private String id;

    private T data;

    public CrudReqDTO(String id) {
        this.id = id;
    }
}
