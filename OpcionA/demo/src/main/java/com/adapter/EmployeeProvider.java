package com.adapter;

import com.model.Employee;

public interface EmployeeProvider {
    Employee getEmployee(String code);
}
