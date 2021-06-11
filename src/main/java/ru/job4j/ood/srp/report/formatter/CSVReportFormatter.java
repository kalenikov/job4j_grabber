package ru.job4j.ood.srp.report.formatter;

import ru.job4j.ood.srp.report.store.Employee;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import static ru.job4j.ood.srp.report.Config.*;

public class CSVReportFormatter implements Formatter {
    private final String D = ";";

    @Override
    public String format(List<Employee> employees, Map<String, Object> params) {
        NumberFormat nf = (NumberFormat) params.getOrDefault("SALARY_FORMAT", DEFAULT_NUMBER_FORMAT);
        SimpleDateFormat df = (SimpleDateFormat) params.getOrDefault("DATE_FORMAT", DEFAULT_DATE_FORMAT);
        boolean outputDates = (boolean) params.getOrDefault("OUTPUT_DATES", true);

        StringBuilder text = new StringBuilder();
        text.append("name").append(D);
        if (outputDates) {
            text.append("hired").append(D);
            text.append("fired").append(D);
        }
        text.append("salary").append(D);
        text.append(System.lineSeparator());

        for (Employee e : employees) {
            text.append(e.getName()).append(D);
            if (outputDates) {
                text.append(df.format(e.getHired().getTime())).append(D);
                text.append(df.format(e.getFired().getTime())).append(D);
            }
            text.append(nf.format(e.getSalary())).append(D);
            text.append(System.lineSeparator());
        }
        return text.toString();
    }

}
