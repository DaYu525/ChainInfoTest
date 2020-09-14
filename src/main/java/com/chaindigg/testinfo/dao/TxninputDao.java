package com.chaindigg.testinfo.dao;

import com.chaindigg.testinfo.pojo.Txninput;

import java.util.List;

public interface TxninputDao {
    int insert(Txninput record);

    List<Txninput> selectByHash(String hash);

}