DROP TABLE IF EXISTS `t_store`;
CREATE TABLE `t_store` (
 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
 `code` varchar(20) NOT NULL DEFAULT '' COMMENT '店铺编号',
 `name` varchar(20) NOT NULL COMMENT '店铺名称',
 `contacts` varchar(20) NOT NULL COMMENT '联系人',
 `mobile` varchar(64) NOT NULL COMMENT '手机号',
 `address` varchar(64) NOT NULL COMMENT '地址',
 `mobile_md5` varchar(32) NOT NULL COMMENT '手机号md5',
 `deleted` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否删除 0-删除 1-正常',
 `create_user` bigint(20)  COMMENT '创建人user_id',
 `update_user` bigint(20)  COMMENT '更新人user_id',
 `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 PRIMARY KEY (`id`),
 INDEX `idx_code` (code),
 INDEX `idx_name` (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店铺表';