package com.chaindigg.testinfo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.ArrayList;
import java.util.List;

public class GetData {

    /**
     * 获取info区块信息页面json数据对象
     * @param id
     * @return
     * @throws UnirestException
     */
    public JSONObject getBlockData(Integer id) throws UnirestException {
            HttpResponse response =
                    Unirest.get("https://info.chaindigg.com/api/block?coinType=btc&id=" + id + "&hash=&pageSize=" +
                            "100&pageNumber=0&channelId=&normal=normal").asString();
            JSONObject jso = JSON.parseObject(response.getBody().toString());
            JSONObject data = jso.getJSONObject("data");
            return data;
    }

    /**
     * 获取显示交易的最大页码，每页显示100条交易
     * @return
     * @throws UnirestException
     */
    public int getLastPage(Integer id) throws UnirestException {
        JSONObject data = getBlockData(id);
        JSONObject page = data.getJSONObject("page");
        JSONObject pageData = page.getJSONObject("pageData");
        int lastPageNumber = pageData.getInteger("lastPageNumber");
        return lastPageNumber;
    }

    /**
     * 获取一个区块的所有交易hash
     * @param id
     * @return
     * @throws UnirestException
     */
    public List<String> getTxHash(Integer id) throws UnirestException {
        int lastPageNumber = getLastPage(id);
        List<String> hash = new ArrayList<String>();
        if (lastPageNumber == 0) {
            HttpResponse response =
                    Unirest.get("https://info.chaindigg.com/api/block?coinType=btc&id="+id+"&hash=&pageSize=" +
                            "100&pageNumber=" + 0 + "&channelId=&normal=normal").asString();
            JSONObject jso = JSON.parseObject(response.getBody().toString());
            JSONObject data = jso.getJSONObject("data");
            JSONArray txnInfoVoList = data.getJSONArray("txnInfoVoList");
            for (int a = 0; a < txnInfoVoList.size(); a++) {
                JSONObject index = txnInfoVoList.getJSONObject(a);
                hash.add(index.get("hash").toString());
            }
        }else {
            for (int i = 0; i <= lastPageNumber; i++) {
                HttpResponse response =
                        Unirest.get("https://info.chaindigg.com/api/block?coinType=btc&id="+id+"&hash=&pageSize=" +
                                "100&pageNumber=" + i + "&channelId=&normal=normal").asString();
                JSONObject jso = JSON.parseObject(response.getBody().toString());
                JSONObject data = jso.getJSONObject("data");
                JSONArray txnInfoVoList = data.getJSONArray("txnInfoVoList");
                for (int a = 0; a < txnInfoVoList.size(); a++) {
                    JSONObject index = txnInfoVoList.getJSONObject(a);
                    hash.add(index.get("hash").toString());
                }
            }
        }
        return hash;
    }

}