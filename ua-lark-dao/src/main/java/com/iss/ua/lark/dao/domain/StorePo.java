package com.iss.ua.lark.dao.domain;

import com.hanson.mybatis.BasePo;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
/**
 *  * t_store 店铺表
 * @author huhanlin 2023-06-05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("t_store")
public class StorePo extends BasePo implements Serializable {
    /**
     * 
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 店铺编号
     */
    @TableField("code")
    private String code;

    /**
     * 店铺名称
     */
    @TableField("name")
    private String name;

    /**
     * 联系人
     */
    @TableField("contacts")
    private String contacts;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 手机号md5
     */
    @TableField("mobile_md5")
    private String mobileMd5;
}