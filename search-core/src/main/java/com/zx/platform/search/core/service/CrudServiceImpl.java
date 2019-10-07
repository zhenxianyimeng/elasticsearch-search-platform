package com.zx.platform.search.core.service;

import com.alibaba.fastjson.JSON;
import com.zx.platform.search.api.ICrudService;
import com.zx.platform.search.api.dto.req.CrudReqDTO;
import com.zx.platform.search.api.dto.resp.CrudRespDTO;
import com.zx.platform.search.api.exception.SearchException;
import com.zx.platform.search.api.exception.SearchExceptionCodeEnum;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-09-29
 * @time: 20:08
 */
@Service
public class CrudServiceImpl implements ICrudService {

    private static final Logger logger = LoggerFactory.getLogger(ICrudService.class);

    @Autowired
    private RestHighLevelClient client;

    @Override
    public <T> CrudRespDTO<T> get(CrudReqDTO crudReqDTO, Class<T> clazz) throws SearchException {
        CrudRespDTO crudRespDTO = new CrudRespDTO();
        long start = System.currentTimeMillis();
        try {
            GetRequest getRequest = new GetRequest(crudReqDTO.getIndex(), crudReqDTO.getIndex(), crudReqDTO.getId());
            FetchSourceContext fetchSourceContext = new FetchSourceContext(
                    true,
                    crudReqDTO.getIncludes() == null ? null : crudReqDTO.getIncludes().toArray(new String[crudReqDTO.getIncludes().size()]),
                    crudReqDTO.getExcludes() == null ? null : crudReqDTO.getExcludes().toArray(new String[crudReqDTO.getExcludes().size()])
            );
            getRequest.fetchSourceContext(fetchSourceContext);
            getRequest.id(crudReqDTO.getId());
            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
            if (getResponse.isExists()) {
                String source = getResponse.getSourceAsString();
                crudRespDTO.setContent(JSON.parseObject(source, clazz));
            }
        }catch (Exception e){
            logger.error("search get error",e);
            throw new SearchException(SearchExceptionCodeEnum.INNER_ERROR);
        }finally {
            long cost = System.currentTimeMillis() - start;
            logger.info("search get|requestId={}|cost={}ms|req={}|resp={}", crudReqDTO.getRequestId(), cost, crudReqDTO, crudRespDTO );
        }
        return crudRespDTO;
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
