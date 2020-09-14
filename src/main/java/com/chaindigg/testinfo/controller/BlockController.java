package com.chaindigg.testinfo.controller;


import com.chaindigg.testinfo.dao.BlockHeadDao;
import com.chaindigg.testinfo.dao.TxDao;
import com.chaindigg.testinfo.dao.TxninputDao;
import com.chaindigg.testinfo.dao.TxnoutputDao;
import com.chaindigg.testinfo.pojo.*;
import com.chaindigg.testinfo.service.GetInfoData;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/block")
@Api
public class BlockController {

    @Resource
    private BlockHeadDao blockheadDao;

    @Resource
    private TxninputDao txninputDao;

    @Resource
    private TxnoutputDao txnoutputDao;

    @Resource
    private GetInfoData getInfoData;


    /**
     * 插入区块数据
     * @param id
     * @return
     * @throws UnirestException
     */
    @PostMapping("/btc")
    @ApiOperation("爬取数据")
    @ApiImplicitParam(name = "id", value = "区块高度", defaultValue = "1",
            required = true)
    public String insert(@RequestParam(value = "id") List<Integer> id) throws UnirestException {
        getInfoData.insert(id);
        return "{\"message\":\"数据爬取成功\"}";
    }


    /***
     * 获取区块信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("查询区块信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "区块高度",dataType="Integer",defaultValue = "600000",
            required = true)})
    public BlockHead get_block(@PathVariable("id") Integer id)  {
        return blockheadDao.selectById(id);
    }



}
