package com.example.connectors;

import com.example.models.Employee;
import com.example.models.ListEmployee;

import java.util.List;

public class EmployeeConnector {

    private List<Employee> employeeList;

    public EmployeeConnector() {
        // Khởi tạo danh sách nhân viên mẫu
        ListEmployee listEmployee = new ListEmployee();
        this.employeeList = listEmployee.getEmployees();
    }

    public Employee login(String usr, String pwd) {
        for (Employee emp : employeeList) {
            if (emp.getUsername().equalsIgnoreCase(usr) && emp.getPassword().equals(pwd)) {
                return emp; // Đăng nhập thành công
            }
        }
        return null; // Đăng nhập thất bại
    }
}