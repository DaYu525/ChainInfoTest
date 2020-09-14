package com.chaindigg.testinfo.service;

import com.chaindigg.testinfo.dao.TxDao;
import com.chaindigg.testinfo.pojo.BlockHead;
import com.chaindigg.testinfo.pojo.Tx;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TxService {
    @Resource
    private TxDao txDao;
    public Tx getTx(String txHash){
        return  txDao.selectByHash(txHash);
    }
}
