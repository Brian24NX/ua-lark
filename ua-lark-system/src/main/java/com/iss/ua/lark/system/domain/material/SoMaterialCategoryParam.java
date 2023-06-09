package com.iss.ua.lark.system.domain.material;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 物料类别param
 * 
 * @author times
 * @date 2023-06-08
 */
@ApiModel(value = "物料参数")
public class SoMaterialCategoryParam {

    private static final long serialVersionUID = -922851360497796481L;
    /** 主键 */
    private Long cid;

    /** 根类别id */
    @ApiModelProperty(value = "根类别id")
    private Long rootId;

    /** 父类别id */
    @ApiModelProperty(value = "父类别id")
    private Long parentId;

    /** 类别名 */
    @ApiModelProperty(value = "类别名")
    private String categoryName;

    /** 类别路径 */
    @ApiModelProperty(value = "类别路径")
    private String categoryPath;

    /** 排序字段 */
    @ApiModelProperty(value = "排序字段")
    private Long corder;

    /** 创建人唯一code */
    @ApiModelProperty(value = "创建人唯一code")
    private String createUser;

    /** 更新人唯一code */
    @ApiModelProperty(value = "更新人唯一code")
    private String updateUser;

    /** 租户code */
    @ApiModelProperty(value = "租户code")
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
