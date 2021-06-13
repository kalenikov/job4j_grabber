package ru.job4j.ood.srp.report.formatter;

import com.google.gson.*;
import ru.job4j.ood.srp.report.store.Employee;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class JSONReportFormatter implements Formatter {
    final Gson gson = new GsonBuilder()
            .registerTypeHierarchyAdapter(Calendar.class, new CalendarSerializer())
            .create();

    @Override
    public String format(List<Employee> employees, Map<String, Object> params) {
        return gson.toJson(employees);
    }

    private static class CalendarSerializer implements JsonSerializer<Calendar> {
        @Override
        public JsonElement serialize(Calendar src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.getTimeInMillis());
        }
    }
}
