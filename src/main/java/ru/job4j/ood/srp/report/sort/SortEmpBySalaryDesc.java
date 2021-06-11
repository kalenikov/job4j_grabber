package ru.job4j.ood.srp.report.sort;

import ru.job4j.ood.srp.report.store.Employee;

import java.util.Comparator;

public class SortEmpBySalaryDesc implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.compare(o2.getSalary(), o1.getSalary());
    }
}
