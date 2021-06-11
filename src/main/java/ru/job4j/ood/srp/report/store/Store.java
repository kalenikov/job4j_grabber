package ru.job4j.ood.srp.report.store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface Store {
    List<Employee> findBy(Predicate<Employee> filter, Comparator<Employee> sort);
}