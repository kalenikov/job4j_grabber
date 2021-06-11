package ru.job4j.ood.srp.report.store;

import ru.job4j.ood.srp.report.sort.SortEmpByName;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemStore implements Store {

    private final List<Employee> employees = new ArrayList<>();

    public void add(Employee em) {
        employees.add(em);
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter, Comparator<Employee> sort) {
        if (filter == null) {
            filter = x -> true;
        }
        if (sort == null) {
            sort = new SortEmpByName();
        }
        return employees.stream()
                .filter(filter)
                .sorted(sort)
                .collect(Collectors.toList());
    }
}