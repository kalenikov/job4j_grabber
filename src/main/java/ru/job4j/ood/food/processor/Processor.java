package ru.job4j.ood.food.processor;

import java.util.List;

public interface Processor<T> {
    void process(List<T> items);
    void resort(List<T> items);
}
