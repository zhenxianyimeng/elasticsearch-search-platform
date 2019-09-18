package com.zx.platform.search.core;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchCoreApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void contextLoads() {
    }


    @Test
    public void searchTest(){
        SearchRequest searchRequest = new SearchRequest();
//        restHighLevelClient.search()
    }

}
