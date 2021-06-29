package ru.job4j.gc;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeakDemo {

    public static void main(String[] args) throws InterruptedException {
//        example1();
//        example2();
        example3();
    }

    private static void example1() throws InterruptedException {
        System.out.println("example1");
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("void finalize");
            }
        };
        WeakReference<Object> weak = new WeakReference<>(object);
        object = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(weak.get());
    }

    private static void example2() throws InterruptedException {
        System.out.println("example2");
        List<WeakReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            objects.add(new WeakReference<>(new Object() {
                @Override
                protected void finalize() {
                    System.out.println("void finalize");
                }
            }));
        }
        System.gc();
        TimeUnit.SECONDS.sleep(3);
    }

    private static void example3() throws InterruptedException {
        System.out.println("example3");
        Object object = new Object() {
            @Override
            protected void finalize(){
                System.out.println("void finalize");
            }
        };
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> weak = new WeakReference<>(object, queue);
        object = null;

        System.gc();

        TimeUnit.SECONDS.sleep(3);
        System.out.println("from link " + weak.get());
        System.out.println("from queue " + queue.poll());
    }

}