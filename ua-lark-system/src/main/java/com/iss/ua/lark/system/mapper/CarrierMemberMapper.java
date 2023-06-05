package com.iss.ua.lark.system.mapper;

import com.iss.ua.lark.system.domain.member.Bu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author iSoftStone-Robert
 * @date 2023/3/17
 */
@Mapper
public interface CarrierMemberMapper {

    /**
     * 获取所有的Bu信息列表
     *
     * @return 所有的Bu信息列表
     */
    List<Bu> getBuList();
}
