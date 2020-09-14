package com.chaindigg.testinfo.dao;

import com.chaindigg.testinfo.pojo.BlockHead;

public interface BlockHeadDao {

    int insert(BlockHead record);

    BlockHead selectById(Integer id);
}