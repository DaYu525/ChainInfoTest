package com.chaindigg.testinfo.dao;

import com.chaindigg.testinfo.pojo.Tx;

import java.util.List;

public interface TxDao {
    int insert(Tx record);

    List<Tx> selectById(Integer id);

    Tx selectByHash(String hash);
}