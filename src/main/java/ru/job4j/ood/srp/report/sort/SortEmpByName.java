package ru.job4j.ood.srp.report.sort;

import ru.job4j.ood.srp.report.store.Employee;

import java.util.Comparator;

public class SortEmpByName implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
