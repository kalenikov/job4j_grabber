package ru.job4j.gc;

import static com.carrotsearch.sizeof.RamUsageEstimator.sizeOf;

public class GCDemo {
    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void memoryUsage() {
        System.out.printf("Free: %d%n", ENVIRONMENT.freeMemory() / MB);
        System.out.printf("Total: %d%n", ENVIRONMENT.totalMemory() / MB);
        System.out.printf("Max: %d%n", ENVIRONMENT.maxMemory() / MB);
    }

    public static void main(String[] args) {
//        System.out.println(sizeOf(new User()));
//        System.out.println(sizeOf(new User(1,"test")));
        memoryUsage();
        for (int i = 0; i < 60000; i++) {
            new User(i, "test" + i);
        }
        memoryUsage();
    }
}
