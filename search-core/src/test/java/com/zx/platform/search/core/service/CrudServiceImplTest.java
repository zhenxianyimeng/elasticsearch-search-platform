package com.zx.platform.search.core.service;

import com.zx.platform.search.api.api.ICrudService;
import com.zx.platform.search.api.dto.req.CrudReqDTO;
import com.zx.platform.search.api.dto.resp.CrudRespDTO;
import com.zx.platform.search.api.exception.SearchException;
import com.zx.platform.search.core.model.Ratings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-10-18
 * @time: 17:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudServiceImplTest {

    @Autowired
    private ICrudService crudService;

    private String TYPE = "ratings";

    private String INDEX = "ratings";

    @Test
    public void get() throws SearchException {
        CrudReqDTO crudReqDTO = new CrudReqDTO();
        crudReqDTO.setIndex(INDEX);
        crudReqDTO.setType(TYPE);
        crudReqDTO.setId("2233");
        CrudRespDTO respDTO = crudService.get(crudReqDTO);
        System.out.println(respDTO.getContent());
    }

    @Test
    public void insert() throws SearchException {
        CrudReqDTO reqDTO = new CrudReqDTO();
        reqDTO.setType(TYPE);
        reqDTO.setIndex(INDEX);
        Ratings ratings = new Ratings();
        ratings.setId(123456L);
        ratings.setComment("你猜我想评论啥");
        reqDTO.setId("123456");
        reqDTO.setData(ratings);
        crudService.insert(reqDTO);
    }

    @Test
    public void update() throws SearchException {
        CrudReqDTO reqDTO = new CrudReqDTO();
        reqDTO.setType(TYPE);
        reqDTO.setIndex(INDEX);
        Ratings ratings = new Ratings();
        ratings.setId(123456L);
        ratings.setComment("你猜我想评论啥!!!");
        reqDTO.setId("123456");
        reqDTO.setData(ratings);
        crudService.update(reqDTO);
    }

    @Test
    public void delete() throws SearchException {
        CrudReqDTO reqDTO = new CrudReqDTO();
        reqDTO.setType(TYPE);
        reqDTO.setIndex(INDEX);
        reqDTO.setId("123456");
        crudService.delete(reqDTO);
    }
}