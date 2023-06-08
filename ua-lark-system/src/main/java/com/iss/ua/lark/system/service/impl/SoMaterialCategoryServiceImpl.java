package com.iss.ua.lark.system.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.iss.ua.lark.common.core.domain.TreeSelect;
import com.iss.ua.lark.common.core.domain.entity.SysDept;
import com.iss.ua.lark.common.utils.DateUtils;
import com.iss.ua.lark.common.utils.StringUtils;
import com.iss.ua.lark.common.utils.spring.SpringUtils;
import com.iss.ua.lark.common.core.domain.entity.SoMaterialCategory;
import com.iss.ua.lark.system.domain.material.SoMaterialCategoryParam;
import com.iss.ua.lark.system.domain.material.SoMaterialCategoryReturn;
import com.iss.ua.lark.system.mapper.material.SoMaterialCategoryMapper;
import com.iss.ua.lark.system.service.ISoMaterialCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 物料类别Service业务层处理
 * 
 * @author times
 * @date 2023-06-08
 */
@Service
public class SoMaterialCategoryServiceImpl implements ISoMaterialCategoryService
{
    @Autowired(required = false)
    private SoMaterialCategoryMapper soMaterialCategoryMapper;

    /**
     * 查询物料类别
     * 
     * @param cid 物料类别主键
     * @return 物料类别
     */
    @Override
    public SoMaterialCategory selectSoMaterialCategoryByCid(Long cid)
    {
        return soMaterialCategoryMapper.selectSoMaterialCategoryByCid(cid);
    }

    /**
     * 查询物料类别列表
     * 
     * @param soMaterialCategory 物料类别
     * @return 物料类别
     */
    @Override
    public List<SoMaterialCategory> selectSoMaterialCategoryList(SoMaterialCategory soMaterialCategory)
    {
        return soMaterialCategoryMapper.selectSoMaterialCategoryList(soMaterialCategory);
    }

    /**
     * 新增物料类别
     * 
     * @param soMaterialCategory 物料类别
     * @return 结果
     */
    @Override
    public int insertSoMaterialCategory(SoMaterialCategory soMaterialCategory)
    {
        soMaterialCategory.setCreateTime(DateUtils.getNowDate());
        return soMaterialCategoryMapper.insertSoMaterialCategory(soMaterialCategory);
    }

    /**
     * 修改物料类别
     * 
     * @param soMaterialCategory 物料类别
     * @return 结果
     */
    @Override
    public int updateSoMaterialCategory(SoMaterialCategory soMaterialCategory)
    {
        soMaterialCategory.setUpdateTime(DateUtils.getNowDate());
        return soMaterialCategoryMapper.updateSoMaterialCategory(soMaterialCategory);
    }

    /**
     * 批量删除物料类别
     * 
     * @param cids 需要删除的物料类别主键
     * @return 结果
     */
    @Override
    public int deleteSoMaterialCategoryByCids(Long[] cids)
    {
        return soMaterialCategoryMapper.deleteSoMaterialCategoryByCids(cids);
    }

    /**
     * 删除物料类别信息
     * 
     * @param cid 物料类别主键
     * @return 结果
     */
    @Override
    public int deleteSoMaterialCategoryByCid(Long cid)
    {
        return soMaterialCategoryMapper.deleteSoMaterialCategoryByCid(cid);
    }

    @Override
    public List<SoMaterialCategoryReturn> selectSoMaterialCategoryList2(SoMaterialCategoryParam param) {
        List<SoMaterialCategory> list = soMaterialCategoryMapper.selectSoMaterialCategoryList2(param);
        return null;
    }

    @Override
    public List<TreeSelect> selectCategoryTreeList(SoMaterialCategory param) {
        List<SoMaterialCategory> categories = SpringUtils.getAopProxy(this).selectSoMaterialCategoryList(param);
        return buildCategoryTreeSelect(categories);
    }
    @Override
    public List<TreeSelect> buildCategoryTreeSelect(List<SoMaterialCategory> categories)
    {
        List<SoMaterialCategory> catgoryTrees = buildCategoryTree(categories);
        return catgoryTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    @Override
    public List<SoMaterialCategory> buildCategoryTree(List<SoMaterialCategory> categories)
    {
        List<SoMaterialCategory> returnList = new ArrayList<SoMaterialCategory>();
        List<Long> tempList = categories.stream().map(SoMaterialCategory::getCid).collect(Collectors.toList());
        for (SoMaterialCategory category : categories)
        {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(category.getParentId()))
            {
                recursionFn(categories, category);
                returnList.add(category);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = categories;
        }
        return returnList;
    }
    /**
     * 递归列表
     */
    private void recursionFn(List<SoMaterialCategory> list, SoMaterialCategory t)
    {
        // 得到子节点列表
        List<SoMaterialCategory> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SoMaterialCategory tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SoMaterialCategory> getChildList(List<SoMaterialCategory> list, SoMaterialCategory t) {
        List<SoMaterialCategory> tlist = new ArrayList<SoMaterialCategory>();
        Iterator<SoMaterialCategory> it = list.iterator();
        while (it.hasNext()) {
            SoMaterialCategory n = (SoMaterialCategory) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getCid().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SoMaterialCategory> list, SoMaterialCategory t)
    {
        return getChildList(list, t).size() > 0;
    }
}
