package com.model;

public class Employee {
    private String code;
    private String name;
    private String position;

    public Employee(String code, String name, String position) {
        this.code = code;
        this.name = name;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
