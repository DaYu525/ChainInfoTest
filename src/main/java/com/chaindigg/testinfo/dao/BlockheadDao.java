package com.chaindigg.testinfo.dao;

import com.chaindigg.testinfo.pojo.Blockhead;

import java.util.List;

public interface BlockheadDao {

    int insert(Blockhead record);

    Blockhead selectById(Integer id);
}