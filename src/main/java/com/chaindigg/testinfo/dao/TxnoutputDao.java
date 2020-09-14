package com.chaindigg.testinfo.dao;

import com.chaindigg.testinfo.pojo.Txninput;
import com.chaindigg.testinfo.pojo.Txnoutput;

import java.util.List;

public interface TxnoutputDao {
    int insert(Txnoutput record);

    List<Txnoutput> selectByHash(String hash);
}