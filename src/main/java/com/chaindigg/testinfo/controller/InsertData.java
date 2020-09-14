package com.chaindigg.testinfo.controller;

import com.chaindigg.testinfo.service.DataInsert;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/insert")
@Api
public class InsertData {


    @Resource
    private DataInsert dataInsert;


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
        dataInsert.insert(id);
        return "{\"message\":\"数据爬取成功\"}";
    }
}
