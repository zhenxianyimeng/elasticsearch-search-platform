package com.zx.platform.search.api;

import com.zx.platform.search.api.dto.req.CrudReqDTO;
import com.zx.platform.search.api.dto.resp.CrudRespDTO;
import com.zx.platform.search.api.exception.SearchException;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-26
 * @time: 23:18
 */
public interface ICrudService {

    <T> CrudRespDTO<T> get(CrudReqDTO crudReqDTO, Class<T> clazz) throws SearchException;

    void insert(CrudReqDTO crudReqDTO) throws SearchException;

    void update(CrudReqDTO crudReqDTO) throws SearchException;

    boolean delete(CrudReqDTO crudReqDTO) throws SearchException;
}
