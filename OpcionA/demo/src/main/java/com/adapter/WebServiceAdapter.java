package com.adapter;

import com.model.Employee;
import com.webservice.EmployeeWebService;

public class WebServiceAdapter implements EmployeeProvider {
    private final EmployeeWebService ws = new EmployeeWebService();

    @Override
    public Employee getEmployee(String code) {
        return ws.fetchEmployee(code);
    }
}
