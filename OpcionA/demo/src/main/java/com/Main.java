package com;
import com.adapter.*;
import com.model.Employee;

public class Main {
    public static void main(String[] args) {
        EmployeeProvider dbAdapter = new DatabaseAdapter();
        EmployeeProvider wsAdapter = new WebServiceAdapter();

        System.out.println("=== Desde Base de Datos ===");
        Employee emp1 = dbAdapter.getEmployee("1"); // Codigo en postgres en render
        System.out.println(emp1);

        System.out.println("\n=== Desde Web Service ===");
        Employee emp2 = wsAdapter.getEmployee("user_632"); // Codigo en retool
        System.out.println(emp2);
    }
}
