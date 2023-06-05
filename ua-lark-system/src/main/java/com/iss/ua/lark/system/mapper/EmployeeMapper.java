package com.iss.ua.lark.system.mapper;

import com.iss.ua.lark.system.domain.member.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author iSoftStone-Robert
 * @date 2023/2/23
 */
@Mapper
public interface EmployeeMapper {

    /**
     * 获取员工用户查询列表
     *
     * @param queryParam 查询参数
     * @return 员工查询列表
     */
    List<Employee> getEmployeeSearchList(String queryParam);

}
