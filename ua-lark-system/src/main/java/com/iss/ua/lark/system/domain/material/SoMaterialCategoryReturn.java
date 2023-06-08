package com.iss.ua.lark.system.domain.material;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 物料类别param
 * 
 * @author times
 * @date 2023-06-08
 */
public class SoMaterialCategoryReturn implements Serializable {

    private static final long serialVersionUID = -1738622963321589348L;
    /** 主键 */
    private Long cid;

    /** 根类别id */
    @ApiModelProperty(name = "根类别id")
    private Long rootId;

    /** 父类别id */
    @ApiModelProperty(name = "父类别id")
    private Long parentId;

    /** 类别名 */
    @ApiModelProperty(name = "类别名")
    private String categoryName;

    /** 类别路径 */
    @ApiModelProperty(name = "类别路径")
    private String categoryPath;

    /** 排序字段 */
    @ApiModelProperty(name = "排序字段")
    private Long corder;

    /** 创建人唯一code */
    @ApiModelProperty(name = "创建人唯一code")
    private String createUser;

    /** 更新人唯一code */
    @ApiModelProperty(name = "更新人唯一code")
    private String updateUser;

    /** 租户code */
    @ApiModelProperty(name = "租户code")
    private String tenantCode;

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
            .append("tenantCode", getTenantCode())
            .toString();
    }
}
