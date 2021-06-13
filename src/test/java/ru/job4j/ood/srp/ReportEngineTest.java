package ru.job4j.ood.srp;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.ood.srp.report.ReportEngine;
import ru.job4j.ood.srp.report.formatter.JSONReportFormatter;
import ru.job4j.ood.srp.report.formatter.XMLReportFormatter;
import ru.job4j.ood.srp.report.store.Employee;
import ru.job4j.ood.srp.report.store.MemStore;
import ru.job4j.ood.srp.report.formatter.CSVReportFormatter;
import ru.job4j.ood.srp.report.formatter.HTMLReportFormatter;
import ru.job4j.ood.srp.report.sort.SortEmpBySalaryDesc;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReportEngineTest {

    private static final MemStore store = new MemStore();

    @BeforeClass
    public static void setUp() {
        Calendar date = new GregorianCalendar(2020, 1, 25);
        store.add(new Employee("emp1", date, date, 100));
        store.add(new Employee("emp2", date, date, 200));
    }

    @Test
    public void whenCSVGenerated() {
        ReportEngine engine = new ReportEngine();
        engine.setStore(store);
        engine.setFormatter(new CSVReportFormatter());
        String expect = "name;hired;fired;salary;" + System.lineSeparator() +
                "emp1;25-02-2020;25-02-2020;100;" + System.lineSeparator() +
                "emp2;25-02-2020;25-02-2020;200;" + System.lineSeparator();
        assertThat(engine.generate(), is(expect));
    }

    @Test
    public void whenHTMLGenerated() {
        ReportEngine engine = new ReportEngine();
        engine.setStore(store);
        engine.setFormatter(new HTMLReportFormatter());
        String expect = "<html><body><table>" +
                "<th>name</th><th>hired</th><th>fired</th><th>salary</th>" +
                "<tr><td>emp1</td><td>25-02-2020</td><td>25-02-2020</td><td>100</td></tr>" +
                "<tr><td>emp2</td><td>25-02-2020</td><td>25-02-2020</td><td>200</td></tr>" +
                "</table></body><html>";
        assertThat(engine.generate(), is(expect));
    }

    @Test
    public void whenCustomFieldsAndDescSortingGenerated() {
        ReportEngine engine = new ReportEngine();
        engine.setStore(store);
        engine.setFormatter(new CSVReportFormatter());
        engine.setParams(Map.of("OUTPUT_DATES", false));
        engine.setComparator(new SortEmpBySalaryDesc());
        String expect = "name;salary;" + System.lineSeparator() +
                "emp2;200;" + System.lineSeparator() +
                "emp1;100;" + System.lineSeparator() ;
        assertThat(engine.generate(), is(expect));
    }

    @Test
    public void whenCustomSalaryFormatGenerated() {
        ReportEngine engine = new ReportEngine();
        engine.setStore(store);
        engine.setFormatter(new CSVReportFormatter());
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.getDefault());
        dfs.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#0.000", dfs);
        engine.setParams(Map.of("SALARY_FORMAT", df));
        String expect = "name;hired;fired;salary;" + System.lineSeparator() +
                "emp1;25-02-2020;25-02-2020;100.000;" + System.lineSeparator() +
                "emp2;25-02-2020;25-02-2020;200.000;" + System.lineSeparator() ;
        assertThat(engine.generate(), is(expect));
    }

    @Test
    public void whenJsonGenerated() {
        ReportEngine engine = new ReportEngine();
        engine.setStore(store);
        engine.setFormatter(new JSONReportFormatter());
        String expect = "[" +
                "{\"name\":\"emp1\",\"hired\":1582578000000,\"fired\":1582578000000,\"salary\":100.0}," +
                "{\"name\":\"emp2\",\"hired\":1582578000000,\"fired\":1582578000000,\"salary\":200.0}" +
                "]";
        assertThat(engine.generate(), is(expect));
    }

    @Test
    public void whenXMLGenerated() {
        ReportEngine engine = new ReportEngine();
        engine.setStore(store);
        engine.setFormatter(new XMLReportFormatter());
        String expect = "<ArrayList>" +
                "<item><name>emp1</name><hired>1582578000000</hired><fired>1582578000000</fired><salary>100.0</salary></item>" +
                "<item><name>emp2</name><hired>1582578000000</hired><fired>1582578000000</fired><salary>200.0</salary></item>" +
                "</ArrayList>";
        assertThat(engine.generate(), is(expect));
    }
}