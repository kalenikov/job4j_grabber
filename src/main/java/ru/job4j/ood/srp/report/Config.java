package ru.job4j.ood.srp.report;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class Config {
    public static final NumberFormat DEFAULT_NUMBER_FORMAT = new DecimalFormat("#");
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
}
