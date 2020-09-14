package com.chaindigg.testinfo;

import com.chaindigg.testinfo.utils.GetData;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestInfoTests {

    @Test
    void contextLoads() throws UnirestException {
        GetData getData = new GetData();
        List<String> txHash = getData.getTxHash(1);
        Assert.assertEquals("0e3e2357e806b6cdb1f70b54c3a3a17b6714ee1f0e68bebb44a74b1efd512098",txHash.get(0));
    }
}
