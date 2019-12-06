package com.zx.platform.search.core.service;

import com.zx.platform.search.api.api.IAggService;
import com.zx.platform.search.api.api.ICrudService;
import com.zx.platform.search.api.constants.AggTypeEnum;
import com.zx.platform.search.api.constants.FieldFilterTypeEnum;
import com.zx.platform.search.api.dto.common.FieldAgg;
import com.zx.platform.search.api.dto.common.FieldFilter;
import com.zx.platform.search.api.dto.req.AggReqDTO;
import com.zx.platform.search.api.dto.req.FilterReqDTO;
import com.zx.platform.search.api.dto.resp.AggRespDTO;
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
 * @date: 2019-12-06
 * @time: 18:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AggServiceTest {

    @Autowired
    private ICrudService crudService;

    private String TYPE = "ratings";

    private String INDEX = "ratings";

    @Autowired
    private IAggService aggService;

    @Test
    public void aggTest() throws SearchException {
        List<FieldFilter> must = new ArrayList<>();
        must.add(new FieldFilter("rating", FieldFilterTypeEnum.TERM, 3));
        AggReqDTO reqDTO = new AggReqDTO();
        reqDTO.setIndex(INDEX);
        reqDTO.setMustFields(must);

        List<FieldAgg> aggs = new ArrayList<>();
        aggs.add(new FieldAgg("agg1", "rating", AggTypeEnum.TERMS));
        reqDTO.setAggList(aggs);

        AggRespDTO aggRespDTO = aggService.agg(reqDTO);
        System.out.println(aggRespDTO);
    }
}
