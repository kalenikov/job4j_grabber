package ru.job4j.ood.srp.report.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.job4j.ood.srp.report.store.Employee;

import java.util.List;
import java.util.Map;

public class XMLReportFormatter implements Formatter {
    private XmlMapper mapper = new XmlMapper();

    @Override
    public String format(List<Employee> employees, Map<String, Object> params) {
        try {
            return mapper.writeValueAsString(employees);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
