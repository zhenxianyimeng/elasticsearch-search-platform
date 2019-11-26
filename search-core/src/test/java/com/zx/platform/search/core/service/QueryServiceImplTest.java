package com.zx.platform.search.core.service;

import com.zx.platform.search.api.api.IQueryService;
import com.zx.platform.search.api.dto.req.FilterReqDTO;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-11-25
 * @time: 19:46
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryServiceImplTest {

    private String INDEX = "ratings";

    @Autowired
    private IQueryService queryService;

    public void filterTest(){
        FilterReqDTO reqDTO = FilterReqDTO.builder()
                .index(INDEX)
                .build();
    }
}
