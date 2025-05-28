package com.adapter;

import com.database.EmployeeFromDatabase;
import com.model.Employee;

public class DatabaseAdapter implements EmployeeProvider {
    private final EmployeeFromDatabase db = new EmployeeFromDatabase();

    @Override
    public Employee getEmployee(String code) {
        return db.getByCode(code);
    }
}
