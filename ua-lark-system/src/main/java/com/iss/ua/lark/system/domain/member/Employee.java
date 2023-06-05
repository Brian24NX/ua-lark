package com.iss.ua.lark.system.domain.member;

/**
 * @author iSoftStone-Robert
 * @date 2023/2/23
 */
public class Employee {

    /**
     * 员工编号
     */
    private String employeeNumber;

    /**
     * 员工中文名称
     */
    private String name;

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
