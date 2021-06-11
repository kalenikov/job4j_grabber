package ru.job4j.ood.srp.report.formatter;

import ru.job4j.ood.srp.report.store.Employee;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import static ru.job4j.ood.srp.report.Config.*;

public class HTMLReportFormatter implements Formatter {

    @Override
    public String format(List<Employee> employees, Map<String, Object> params) {
        NumberFormat nf = (NumberFormat) params.getOrDefault("SALARY_FORMAT", DEFAULT_NUMBER_FORMAT);
        SimpleDateFormat df = (SimpleDateFormat) params.getOrDefault("DATE_FORMAT", DEFAULT_DATE_FORMAT);
        boolean outputDates = (boolean) params.getOrDefault("OUTPUT_DATES", true);

        StringBuilder text = new StringBuilder();
        text.append("<html><body><table>");
        text.append("<th>name</th>");
        if (outputDates) {
            text.append("<th>hired</th>");
            text.append("<th>fired</th>");
        }
        text.append("<th>salary</th>");

        for (Employee e : employees) {
            text.append("<tr>");
            text.append("<td>").append(e.getName()).append("</td>");
            if (outputDates) {
                text.append("<td>").append(df.format(e.getHired().getTime())).append("</td>");
                text.append("<td>").append(df.format(e.getFired().getTime())).append("</td>");
            }
            text.append("<td>").append(nf.format(e.getSalary())).append("</td>");
            text.append("</tr>");
        }
        text.append("</table></body><html>");
        return text.toString();
    }

}
