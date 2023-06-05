package com.iss.ua.lark.common.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 店铺表
 * </p>
 *
 * @author Hanson
 * @since 2023-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_store")
@ApiModel(value="店铺表对象", description="店铺表")
public class StoreBo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "店铺编号")
    private String code;

    @ApiModelProperty(value = "店铺名称")
    private String name;

    @ApiModelProperty(value = "联系人")
    private String contacts;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "手机号md5")
    private String mobileMd5;

    @ApiModelProperty(value = "是否删除 0-删除 1-正常")
    private Integer deleted;

    @ApiModelProperty(value = "创建人user_id")
    private Long createUser;

    @ApiModelProperty(value = "更新人user_id")
    private Long updateUser;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
