package com.iss.ua.lark.common.core.domain.entity;

import com.iss.ua.lark.common.annotation.Excel;
import com.iss.ua.lark.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 物料类别对象 so_material_category
 * 
 * @author times
 * @date 2023-06-08
 */
public class SoMaterialCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long cid;

    /** 根类别id */
    @Excel(name = "根类别id")
    private Long rootId;

    /** 父类别id */
    @Excel(name = "父类别id")
    private Long parentId;

    /** 类别名 */
    @Excel(name = "类别名")
    private String categoryName;

    /** 类别路径 */
    @Excel(name = "类别路径")
    private String categoryPath;

    /** 排序字段 */
    @Excel(name = "排序字段")
    private Long corder;

    /** 创建人唯一code */
    @Excel(name = "创建人唯一code")
    private String createUser;

    /** 更新人唯一code */
    @Excel(name = "更新人唯一code")
    private String updateUser;

    /** 租户code */
    @Excel(name = "租户code")
    private String tenantCode;
    /** 子菜单 */
    private List<SoMaterialCategory> children = new ArrayList<SoMaterialCategory>();

    public List<SoMaterialCategory> getChildren() {
        return children;
    }

    public void setChildren(List<SoMaterialCategory> children) {
        this.children = children;
    }

    public void setCid(Long cid)
    {
        this.cid = cid;
    }

    public Long getCid() 
    {
        return cid;
    }
    public void setRootId(Long rootId) 
    {
        this.rootId = rootId;
    }

    public Long getRootId() 
    {
        return rootId;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setCategoryName(String categoryName) 
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName() 
    {
        return categoryName;
    }
    public void setCategoryPath(String categoryPath) 
    {
        this.categoryPath = categoryPath;
    }

    public String getCategoryPath() 
    {
        return categoryPath;
    }
    public void setCorder(Long corder) 
    {
        this.corder = corder;
    }

    public Long getCorder() 
    {
        return corder;
    }
    public void setCreateUser(String createUser) 
    {
        this.createUser = createUser;
    }

    public String getCreateUser() 
    {
        return createUser;
    }
    public void setUpdateUser(String updateUser) 
    {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() 
    {
        return updateUser;
    }
    public void setTenantCode(String tenantCode) 
    {
        this.tenantCode = tenantCode;
    }

    public String getTenantCode() 
    {
        return tenantCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cid", getCid())
            .append("rootId", getRootId())
            .append("parentId", getParentId())
            .append("categoryName", getCategoryName())
            .append("categoryPath", getCategoryPath())
            .append("corder", getCorder())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("tenantCode", getTenantCode())
            .toString();
    }
}
