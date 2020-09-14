package com.chaindigg.testinfo.controller;


import com.chaindigg.testinfo.dao.BlockHeadDao;
import com.chaindigg.testinfo.dao.TxDao;
import com.chaindigg.testinfo.dao.TxninputDao;
import com.chaindigg.testinfo.dao.TxnoutputDao;
import com.chaindigg.testinfo.pojo.*;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@ResponseBody
@RequestMapping("/get")
@Api
public class GetData {

    @Resource
    private BlockHeadDao blockheadDao;

    @Resource
    private TxninputDao txninputDao;

    @Resource
    private TxnoutputDao txnoutputDao;

    @Resource
    private TxDao txDao;

    /***
     * 获取区块信息
     * @param id
     * @return
     */
    @GetMapping("/block/{id}")
    @ApiOperation("查询区块信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "区块高度",dataType="Integer",defaultValue = "600000",
            required = true)})
    public BlockHead get_block(@PathVariable("id") Integer id)  {
        return blockheadDao.selectById(id);
    }


    /***
     * 获取交易信息
     * @param txhash
     * @return
     */
    @GetMapping("/txinfo/{txhash}")
    @ApiOperation("查询交易详情")
    @ApiImplicitParam(name = "txhash", value = "交易哈希",dataType = "String",defaultValue =
            "e97b737fc02583283d199dcad797c2a58f534571a4a6b19fbba93088ff596f2c",
            required = true)
    public Tx get_txinfo(@PathVariable("txhash")String txhash)  {
        return  txDao.selectByHash(txhash);
    }
}
