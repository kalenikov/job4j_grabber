package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.report.store.Employee;
import ru.job4j.ood.srp.report.store.Store;
import ru.job4j.ood.srp.report.formatter.Formatter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;


public class ReportEngine implements Report {
    private Store store;
    private Formatter formatter;
    private Comparator<Employee> comparator;
    private Predicate<Employee> filter;
    private Map<String, Object> params;

    public ReportEngine() {
        params = new HashMap<>();
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }

    public void setComparator(Comparator<Employee> comparator) {
        this.comparator = comparator;
    }

    public void setFilter(Predicate<Employee> filter) {
        this.filter = filter;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public String generate() {
        List<Employee> list = store.findBy(filter, comparator);
        return formatter.format(list, params);
    }
}
