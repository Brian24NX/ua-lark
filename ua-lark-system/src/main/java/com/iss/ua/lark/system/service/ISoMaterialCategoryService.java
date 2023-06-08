package com.iss.ua.lark.system.service;

import java.util.List;

import com.iss.ua.lark.common.core.domain.TreeSelect;
import com.iss.ua.lark.common.core.domain.entity.SysDept;
import com.iss.ua.lark.common.core.domain.entity.SoMaterialCategory;
import com.iss.ua.lark.system.domain.material.SoMaterialCategoryParam;
import com.iss.ua.lark.system.domain.material.SoMaterialCategoryReturn;

/**
 * 物料类别Service接口
 * 
 * @author times
 * @date 2023-06-08
 */
public interface ISoMaterialCategoryService 
{
    /**
     * 查询物料类别
     * 
     * @param cid 物料类别主键
     * @return 物料类别
     */
    public SoMaterialCategory selectSoMaterialCategoryByCid(Long cid);

    /**
     * 查询物料类别列表
     * 
     * @param soMaterialCategory 物料类别
     * @return 物料类别集合
     */
    public List<SoMaterialCategory> selectSoMaterialCategoryList(SoMaterialCategory soMaterialCategory);

    /**
     * 新增物料类别
     * 
     * @param soMaterialCategory 物料类别
     * @return 结果
     */
    public int insertSoMaterialCategory(SoMaterialCategory soMaterialCategory);

    /**
     * 修改物料类别
     * 
     * @param soMaterialCategory 物料类别
     * @return 结果
     */
    public int updateSoMaterialCategory(SoMaterialCategory soMaterialCategory);

    /**
     * 批量删除物料类别
     * 
     * @param cids 需要删除的物料类别主键集合
     * @return 结果
     */
    public int deleteSoMaterialCategoryByCids(Long[] cids);

    /**
     * 删除物料类别信息
     * 
     * @param cid 物料类别主键
     * @return 结果
     */
    public int deleteSoMaterialCategoryByCid(Long cid);

    List<SoMaterialCategory> selectSoMaterialCategoryList2(SoMaterialCategory param);

    List<TreeSelect> selectCategoryTreeList(SoMaterialCategory param);
    List<TreeSelect> buildCategoryTreeSelect(List<SoMaterialCategory> categories);
    List<SoMaterialCategory> buildCategoryTree(List<SoMaterialCategory> categories);
}
