package com.chaindigg.testinfo.controller;

import com.chaindigg.testinfo.dao.TxDao;
import com.chaindigg.testinfo.pojo.Tx;
import com.chaindigg.testinfo.service.GetInfoData;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/tx")
@Api
public class TxController {


    @Resource
    private TxDao txDao;


    /***
     * 获取交易信息
     * @param txhash
     * @return
     */
    @GetMapping("/{txhash}")
    @ApiOperation("查询交易详情")
    @ApiImplicitParam(name = "txhash", value = "交易哈希", dataType = "String", defaultValue =
            "e97b737fc02583283d199dcad797c2a58f534571a4a6b19fbba93088ff596f2c",
            required = true)
    public Tx get_txinfo(@PathVariable("txhash") String txhash) {
        return txDao.selectByHash(txhash);
    }
}
