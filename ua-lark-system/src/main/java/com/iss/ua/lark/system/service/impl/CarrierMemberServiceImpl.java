package com.iss.ua.lark.system.service.impl;

import com.iss.ua.lark.system.domain.member.Bu;
import com.iss.ua.lark.system.mapper.CarrierMemberMapper;
import com.iss.ua.lark.system.service.ICarrierMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author iSoftStone-Robert
 * @date 2023/3/17
 */
@Service
public class CarrierMemberServiceImpl implements ICarrierMemberService {

    @Autowired
    private CarrierMemberMapper carrierMemberMapper;

    /**
     * 获取所有的Bu信息列表
     *
     * @return 所有的Bu信息列表
     */
    public List<Bu> getBuList() {
        return carrierMemberMapper.getBuList();
    }

}
