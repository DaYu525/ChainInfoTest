package com.chaindigg.testinfo.dao;

import com.chaindigg.testinfo.pojo.TxnOutput;

import java.util.List;

public interface TxnoutputDao {
    int insert(TxnOutput record);

    List<TxnOutput> selectByHash(String hash);
}