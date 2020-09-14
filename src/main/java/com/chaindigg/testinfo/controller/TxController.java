package com.chaindigg.testinfo.controller;

import com.chaindigg.testinfo.pojo.Tx;
import com.chaindigg.testinfo.service.TxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@ResponseBody
@RequestMapping("/tx")
@Api
public class TxController {

    @Resource
    private TxService txService;



    /***
     * 获取交易信息
     * @param txHash
     * @return
     */
    @GetMapping("/{txHash}")
    @ApiOperation("查询交易详情")
    @ApiImplicitParam(name = "txHash", value = "交易哈希",dataType = "String",defaultValue =
            "e97b737fc02583283d199dcad797c2a58f534571a4a6b19fbba93088ff596f2c",
            required = true)
    public Tx get_txinfo(@PathVariable("txHash")String txHash)  {
        return  txService.getTx(txHash);
    }
}
