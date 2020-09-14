package com.chaindigg.testinfo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chaindigg.testinfo.dao.BlockHeadDao;
import com.chaindigg.testinfo.dao.TxDao;
import com.chaindigg.testinfo.pojo.BlockHead;
import com.chaindigg.testinfo.pojo.Tx;
import com.chaindigg.testinfo.pojo.TxnOutput;
import com.chaindigg.testinfo.dao.TxninputDao;
import com.chaindigg.testinfo.dao.TxnoutputDao;
import com.chaindigg.testinfo.pojo.TxnInput;
import com.chaindigg.testinfo.utils.GetData;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class DataInsert {

    @Resource
    private BlockHeadDao blockheadDao;

    @Resource
    private TxninputDao txninputDao;

    @Resource
    private TxDao txDao;

    @Resource
    private TxnoutputDao txnoutputDao;

    /**
     * 爬取及插入数据
     * @param id
     * @throws UnirestException
     */
    public void insert(List<Integer> id) throws UnirestException {
        TxnInput txninput = new TxnInput();
        TxnOutput txnoutput = new TxnOutput();
        GetData getData = new GetData();
        for (Integer height : id) {
            //插入BlockHead
            JSONObject data = getData.getBlockData(height);
            BlockHead bh = JSON.parseObject(data.toString(), BlockHead.class);
            JSONObject pBlockVo = data.getJSONObject("pBlockVo");
            bh.setSize(pBlockVo.getInteger("size"));
            bh.setVersion(pBlockVo.getInteger("version"));
            bh.setNonce(pBlockVo.getString("nonce"));
            bh.setMerkleRoot(pBlockVo.getString("merkleRoot"));
            bh.setTime(pBlockVo.getString("time"));
            bh.setConfirms(pBlockVo.getInteger("confirms"));
            blockheadDao.insert(bh);
            //插入Tx
            List<String> hash = getData.getTxHash(height);
            for (int i = 0; i < hash.size(); i++) {
                String hashCode = hash.get(i);
                HttpResponse response =
                        Unirest.get("https://info.chaindigg.com/api/txn?coinType=btc&" +
                                "hash="+hashCode+"&channelId=").asString();
                JSONObject jso = JSON.parseObject(response.getBody().toString());
                JSONObject TxData = jso.getJSONObject("data");
                Tx tx = JSON.parseObject(TxData.toString(), Tx.class);
                txDao.insert(tx);
                //插入input
                JSONArray inputVos = TxData.getJSONArray("inputVos");
                if (inputVos!=null) {
                    for (int a = 0; a < inputVos.size(); a++) {
                        JSONObject index = inputVos.getJSONObject(a);
                        txninput.setInputAddress(index.get("addressHash").toString());
                        txninput.setInputValue(index.get("valueStr").toString());
                        txninput.setTxnHash(hashCode);
                        txninputDao.insert(txninput);

                    }
                }else{
                    txninput.setTxnHash(hashCode);
                    txninput.setInputAddress("coinbase");
                    txninput.setInputValue("0");
                    txninputDao.insert(txninput);
                }
                //插入output
                JSONArray outputInfos =TxData.getJSONArray("outputInfos");
                if (outputInfos!=null) {
                    for (int b = 0; b < outputInfos.size(); b++) {
                        JSONObject index = outputInfos.getJSONObject(b);
                        JSONObject outptVo = index.getJSONObject("outptVo");
                        txnoutput.setOutputAddress(outptVo.get("addressHash").toString());
                        txnoutput.setOutputValue(outptVo.get("valueStr").toString());
                        txnoutput.setTxnHash(hashCode);
                        txnoutputDao.insert(txnoutput);
                    }
                }else{
                    txnoutput.setTxnHash(hashCode);
                    txnoutput.setOutputAddress("地址解析失败");
                    txnoutput.setOutputValue("0");
                    txnoutputDao.insert(txnoutput);
                }

            }
        }

    }

}
