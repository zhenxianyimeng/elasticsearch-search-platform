package com.zx.platform.search.core.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zx.platform.search.api.ICrudService;
import com.zx.platform.search.api.dto.req.CrudReqDTO;
import com.zx.platform.search.api.dto.resp.CrudRespDTO;
import com.zx.platform.search.api.exception.SearchException;
import com.zx.platform.search.api.exception.SearchExceptionCodeEnum;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
        CrudRespDTO respDTO = get(crudReqDTO);
        Map<String, Object> source = (Map<String, Object>) respDTO.getContent();
        respDTO.setContent(JSONObject.parseObject(JSON.toJSONString(source), clazz));
        return respDTO;
    }

    @Override
    public CrudRespDTO get(CrudReqDTO crudReqDTO) throws SearchException {
        CrudRespDTO crudRespDTO = new CrudRespDTO();
        long start = System.currentTimeMillis();
        try {
            GetRequest getRequest = new GetRequest(crudReqDTO.getIndex(), crudReqDTO.getType(), crudReqDTO.getId());
            FetchSourceContext fetchSourceContext = new FetchSourceContext(
                    true,
                    crudReqDTO.getIncludes() == null ? null : crudReqDTO.getIncludes().toArray(new String[crudReqDTO.getIncludes().size()]),
                    crudReqDTO.getExcludes() == null ? null : crudReqDTO.getExcludes().toArray(new String[crudReqDTO.getExcludes().size()])
            );
            getRequest.fetchSourceContext(fetchSourceContext);
            getRequest.id(crudReqDTO.getId());
            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
            if (getResponse.isExists()) {
                Map<String, Object> source = getResponse.getSourceAsMap();
                crudRespDTO.setContent(source);
            }
        } catch (Exception e) {
            logger.error("search get error", e);
            throw new SearchException(SearchExceptionCodeEnum.INNER_ERROR);
        } finally {
            long cost = System.currentTimeMillis() - start;
            logger.info("search get|requestId={}|cost={}ms|req={}|resp={}", crudReqDTO.getRequestId(), cost, crudReqDTO, crudRespDTO);
        }
        return crudRespDTO;
    }

    @Override
    public void insert(CrudReqDTO crudReqDTO) throws SearchException {
        long start = System.currentTimeMillis();
        try {
            IndexRequest indexRequest = new IndexRequest(crudReqDTO.getIndex(), crudReqDTO.getType(), crudReqDTO.getId());
            indexRequest.source(JSON.toJSONString(crudReqDTO.getData()), XContentType.JSON);
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            logger.error("search insert error", e);
            throw new SearchException(SearchExceptionCodeEnum.INNER_ERROR);
        } finally {
            long cost = System.currentTimeMillis() - start;
            logger.info("search insert|requestId={}|cost={}ms|req={}", crudReqDTO.getRequestId(), cost, crudReqDTO);
        }
    }

    @Override
    public void update(CrudReqDTO crudReqDTO) throws SearchException {
        long start = System.currentTimeMillis();
        try {
            UpdateRequest updateRequest = new UpdateRequest(crudReqDTO.getIndex(), crudReqDTO.getType(), crudReqDTO.getId());
            updateRequest.doc(JSON.toJSONString(crudReqDTO.getData()), XContentType.JSON);
            UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            logger.error("search update error", e);
            throw new SearchException(SearchExceptionCodeEnum.INNER_ERROR);
        } finally {
            long cost = System.currentTimeMillis() - start;
            logger.info("search update|requestId={}|cost={}ms|req={}", crudReqDTO.getRequestId(), cost, crudReqDTO);
        }
    }

    @Override
    public boolean delete(CrudReqDTO crudReqDTO) throws SearchException {
        long start = System.currentTimeMillis();
        try {
            DeleteRequest deleteRequest = new DeleteRequest(crudReqDTO.getIndex(), crudReqDTO.getType(), crudReqDTO.getId());
            DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
            if(deleteResponse.getResult() == DocWriteResponse.Result.DELETED){
                return true;
            }else {
                logger.error("search delete error|response={}",deleteResponse);
            }
        } catch (Exception e) {
            logger.error("search delete error",e);
            throw new SearchException(SearchExceptionCodeEnum.INNER_ERROR);
        } finally {
            long cost = System.currentTimeMillis() - start;
            logger.info("search delete|requestId={}|cost={}ms|req={}", crudReqDTO.getRequestId(), cost, crudReqDTO);
        }
        return false;
    }
}
