package com.iss.ua.lark.system.service;

import com.iss.ua.lark.system.domain.member.Employee;

import java.util.List;

/**
 * 员工信息服务层
 *
 * @author iSoftStone-Robert
 * @date 2023/2/23
 */
public interface IEmployeeService {

    /**
     * 获取员工用户查询列表
     *
     * @param queryParam 查询参数
     * @return 员工查询列表
     */
    List<Employee> getEmployeeSearchList(String queryParam);

}
