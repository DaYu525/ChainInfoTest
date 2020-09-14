package com.chaindigg.testinfo.dao;

import com.chaindigg.testinfo.pojo.TxnInput;

import java.util.List;

public interface TxninputDao {
    int insert(TxnInput record);

    List<TxnInput> selectByHash(String hash);

}