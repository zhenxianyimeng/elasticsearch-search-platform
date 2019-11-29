package com.zx.platform.search.core.service;

import com.zx.platform.search.api.api.IQueryService;
import com.zx.platform.search.api.constants.FieldFilterTypeEnum;
import com.zx.platform.search.api.dto.common.FieldFilter;
import com.zx.platform.search.api.dto.req.FilterReqDTO;
import com.zx.platform.search.api.dto.resp.HitsRespDTO;
import com.zx.platform.search.api.exception.SearchException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void filterTest() throws SearchException {
        List<FieldFilter> must = new ArrayList<>();
        must.add(new FieldFilter("rating", FieldFilterTypeEnum.TERM, 3));
        FilterReqDTO reqDTO = new FilterReqDTO();
        reqDTO.setIndex(INDEX);
        reqDTO.setMustFields(must);

        HitsRespDTO hitsRespDTO = queryService.filter(reqDTO);
        System.out.println(hitsRespDTO);
    }
}
