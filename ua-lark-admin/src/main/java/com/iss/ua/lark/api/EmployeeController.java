package com.iss.ua.lark.api;

import com.iss.ua.lark.common.core.domain.AjaxResult;
import com.iss.ua.lark.system.domain.member.Employee;
import com.iss.ua.lark.system.service.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author iSoftStone-Robert
 * @date 2023/2/23
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final static Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private IEmployeeService employeeService;

    /**
     * 根据员工名称查询参数，获取员工信息查询结果
     *
     * @param queryParam 员工名称查询参数
     * @return 员工信息查询结果
     */
    @GetMapping("/search/list")
    public AjaxResult searchEmployeeList(@RequestParam(required = false) String queryParam) {
        List<Employee> employeeList = employeeService.getEmployeeSearchList(queryParam);
        return AjaxResult.success(employeeList);
    }

}
