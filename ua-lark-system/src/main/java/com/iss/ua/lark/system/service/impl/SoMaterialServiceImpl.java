package com.iss.ua.lark.system.service.impl;

import java.util.List;

import com.iss.ua.lark.common.utils.DateUtils;
import com.iss.ua.lark.system.domain.material.SoMaterial;
import com.iss.ua.lark.system.mapper.material.SoMaterialMapper;
import com.iss.ua.lark.system.service.ISoMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 物料Service业务层处理
 * 
 * @author times
 * @date 2023-06-08
 */
@Service
public class SoMaterialServiceImpl implements ISoMaterialService
{
    @Autowired(required = false)
    private SoMaterialMapper soMaterialMapper;

    /**
     * 查询物料
     * 
     * @param mid 物料主键
     * @return 物料
     */
    @Override
    public SoMaterial selectSoMaterialByMid(Long mid)
    {
        return soMaterialMapper.selectSoMaterialByMid(mid);
    }

    /**
     * 查询物料列表
     * 
     * @param soMaterial 物料
     * @return 物料
     */
    @Override
    public List<SoMaterial> selectSoMaterialList(SoMaterial soMaterial)
    {
        return soMaterialMapper.selectSoMaterialList(soMaterial);
    }

    /**
     * 新增物料
     * 
     * @param soMaterial 物料
     * @return 结果
     */
    @Override
    public int insertSoMaterial(SoMaterial soMaterial)
    {
        soMaterial.setCreateTime(DateUtils.getNowDate());
        return soMaterialMapper.insertSoMaterial(soMaterial);
    }

    /**
     * 修改物料
     * 
     * @param soMaterial 物料
     * @return 结果
     */
    @Override
    public int updateSoMaterial(SoMaterial soMaterial)
    {
        return soMaterialMapper.updateSoMaterial(soMaterial);
    }

    /**
     * 批量删除物料
     * 
     * @param mids 需要删除的物料主键
     * @return 结果
     */
    @Override
    public int deleteSoMaterialByMids(Long[] mids)
    {
        return soMaterialMapper.deleteSoMaterialByMids(mids);
    }

    /**
     * 删除物料信息
     * 
     * @param mid 物料主键
     * @return 结果
     */
    @Override
    public int deleteSoMaterialByMid(Long mid)
    {
        return soMaterialMapper.deleteSoMaterialByMid(mid);
    }
}
