package com.iss.ua.lark.system.service;

import com.iss.ua.lark.system.domain.member.Bu;

import java.util.List;

/**
 * @author iSoftStone-Robert
 * @date 2023/3/17
 */
public interface ICarrierMemberService {

    /**
     * 获取所有的Bu信息列表
     *
     * @return 所有的Bu信息列表
     */
    List<Bu> getBuList();

}
