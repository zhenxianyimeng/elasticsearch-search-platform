package com.zx.platform.search.core.service;

import com.zx.platform.search.api.ICrudService;
import com.zx.platform.search.api.dto.req.CrudReqDTO;
import com.zx.platform.search.api.dto.resp.CrudRespDTO;
import com.zx.platform.search.api.exception.SearchException;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-29
 * @time: 20:08
 */
@Service
public class CrudServiceImpl implements ICrudService {
    @Override
    public <T> CrudRespDTO<T> get(CrudReqDTO crudReqDTO, Class<T> clazz) throws SearchException {
        return null;
    }

    @Override
    public CrudRespDTO insert(CrudReqDTO crudReqDTO) throws SearchException {
        return null;
    }

    @Override
    public CrudRespDTO update(CrudReqDTO crudReqDTO) throws SearchException {
        return null;
    }
}
