package com.example.swiss.re.service;

public class OUT {
    private Employee employee;
    private double byAmount;
    public OUT(Employee employee, double byAmount) {
        this.employee = employee;
        this.byAmount = byAmount;
    }

    @Override
    public String toString() {
        return "OUT{" +
                "employee=" + employee +
                ", byAmount=" + byAmount +
                '}';
    }

    public Employee getEmployee() {
        return employee;
    }
}
