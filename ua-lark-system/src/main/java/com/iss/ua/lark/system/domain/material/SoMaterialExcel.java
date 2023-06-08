package com.iss.ua.lark.system.domain.material;

import com.iss.ua.lark.common.annotation.Excel;
import com.iss.ua.lark.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 物料对象 so_material
 * 
 * @author times
 * @date 2023-06-08
 */
public class SoMaterialExcel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long mid;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialName;

    /** 物料简称 */
    @Excel(name = "物料简称")
    private String shortName;

    /** 原始物料code */
    @Excel(name = "原始物料code")
    private String oriSkuCode;

    /** ua的物料code,supplier_code拼ori_sku_code */
    @Excel(name = "ua的物料code,supplier_code拼ori_sku_code")
    private String uaSkuCode;

    /** 物料图片id */
    @Excel(name = "物料图片id")
    private Long image;

    /** 物料单位 */
    @Excel(name = "物料单位")
    private String unit;

    /** 零售价格 */
    @Excel(name = "零售价格")
    private Long retailPrice;

    /** 成本价格 */
    @Excel(name = "成本价格")
    private Long costPrice;

    /** 价格单位 */
    @Excel(name = "价格单位")
    private String priceUnit;

    /** 物料规格 */
    @Excel(name = "物料规格")
    private String specifications;

    /** 1上架，2下架 */
    @Excel(name = "1上架，2下架")
    private Integer status;

    /** 审批状态：1审批中，2审批完成 */
    @Excel(name = "审批状态：1审批中，2审批完成")
    private Integer approvalStatus;

    /** 供应商唯一code */
    @Excel(name = "供应商唯一code")
    private String supplierCode;

    /** 类别id */
    @Excel(name = "类别id")
    private Long categoryId;

    /** 创建人唯一code */
    @Excel(name = "创建人唯一code")
    private String createUser;

    /** 更新人唯一code */
    @Excel(name = "更新人唯一code")
    private String updateUser;

    /** 租户code */
    @Excel(name = "租户code")
    private String tenantCode;

    public void setMid(Long mid) 
    {
        this.mid = mid;
    }

    public Long getMid() 
    {
        return mid;
    }
    public void setMaterialName(String materialName) 
    {
        this.materialName = materialName;
    }

    public String getMaterialName() 
    {
        return materialName;
    }
    public void setShortName(String shortName) 
    {
        this.shortName = shortName;
    }

    public String getShortName() 
    {
        return shortName;
    }
    public void setOriSkuCode(String oriSkuCode) 
    {
        this.oriSkuCode = oriSkuCode;
    }

    public String getOriSkuCode() 
    {
        return oriSkuCode;
    }
    public void setUaSkuCode(String uaSkuCode) 
    {
        this.uaSkuCode = uaSkuCode;
    }

    public String getUaSkuCode() 
    {
        return uaSkuCode;
    }
    public void setImage(Long image) 
    {
        this.image = image;
    }

    public Long getImage() 
    {
        return image;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setRetailPrice(Long retailPrice) 
    {
        this.retailPrice = retailPrice;
    }

    public Long getRetailPrice() 
    {
        return retailPrice;
    }
    public void setCostPrice(Long costPrice) 
    {
        this.costPrice = costPrice;
    }

    public Long getCostPrice() 
    {
        return costPrice;
    }
    public void setPriceUnit(String priceUnit) 
    {
        this.priceUnit = priceUnit;
    }

    public String getPriceUnit() 
    {
        return priceUnit;
    }
    public void setSpecifications(String specifications) 
    {
        this.specifications = specifications;
    }

    public String getSpecifications() 
    {
        return specifications;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setApprovalStatus(Integer approvalStatus) 
    {
        this.approvalStatus = approvalStatus;
    }

    public Integer getApprovalStatus() 
    {
        return approvalStatus;
    }
    public void setSupplierCode(String supplierCode) 
    {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() 
    {
        return supplierCode;
    }
    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
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
            .append("mid", getMid())
            .append("materialName", getMaterialName())
            .append("shortName", getShortName())
            .append("oriSkuCode", getOriSkuCode())
            .append("uaSkuCode", getUaSkuCode())
            .append("image", getImage())
            .append("unit", getUnit())
            .append("retailPrice", getRetailPrice())
            .append("costPrice", getCostPrice())
            .append("priceUnit", getPriceUnit())
            .append("specifications", getSpecifications())
            .append("status", getStatus())
            .append("approvalStatus", getApprovalStatus())
            .append("supplierCode", getSupplierCode())
            .append("categoryId", getCategoryId())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("createTime", getCreateTime())
            .append("tenantCode", getTenantCode())
            .toString();
    }
}
