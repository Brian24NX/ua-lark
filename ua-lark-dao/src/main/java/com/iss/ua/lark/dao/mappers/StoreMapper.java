package com.iss.ua.lark.dao.mappers;

import com.hanson.mybatis.MyBaseMapper;
import com.iss.ua.lark.dao.domain.StorePo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

/**
* t_store  店铺表
* @author: huhanlin 2023-06-05 15:33:23
*/
@Mapper
public interface StoreMapper extends MyBaseMapper<StorePo> {
}