package ru.job4j.ood.srp.report.formatter;

import ru.job4j.ood.srp.report.store.Employee;

import java.util.List;
import java.util.Map;

public interface Formatter {
    public String format(List<Employee> employees, Map<String, Object> params);
}
