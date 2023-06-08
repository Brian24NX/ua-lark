package com.iss.ua.lark.system.mapper.material;

import java.util.List;

import com.iss.ua.lark.system.domain.material.SoMaterial;

/**
 * 物料Mapper接口
 * 
 * @author times
 * @date 2023-06-08
 */
public interface SoMaterialMapper 
{
    /**
     * 查询物料
     * 
     * @param mid 物料主键
     * @return 物料
     */
    public SoMaterial selectSoMaterialByMid(Long mid);

    /**
     * 查询物料列表
     * 
     * @param soMaterial 物料
     * @return 物料集合
     */
    public List<SoMaterial> selectSoMaterialList(SoMaterial soMaterial);

    /**
     * 新增物料
     * 
     * @param soMaterial 物料
     * @return 结果
     */
    public int insertSoMaterial(SoMaterial soMaterial);

    /**
     * 修改物料
     * 
     * @param soMaterial 物料
     * @return 结果
     */
    public int updateSoMaterial(SoMaterial soMaterial);

    /**
     * 删除物料
     * 
     * @param mid 物料主键
     * @return 结果
     */
    public int deleteSoMaterialByMid(Long mid);

    /**
     * 批量删除物料
     * 
     * @param mids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSoMaterialByMids(Long[] mids);
}
