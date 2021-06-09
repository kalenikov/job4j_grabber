package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;

public class MaxMin {

    public static void main(String[] args) {
        MaxMin mm = new MaxMin();
        List<Integer> list = List.of(5, 1, 10, 2, -3, -1);
        assert mm.max(list, Comparator.naturalOrder()) == 10;
        assert mm.min(list, Comparator.naturalOrder()) == -3;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return getBy(value, BinaryOperator.maxBy(comparator));
    }

     public <T> T min(List<T> value, Comparator<T> comparator) {
        return getBy(value, BinaryOperator.minBy(comparator));
    }

    private  <T> T getBy(List<T> value, BinaryOperator<T> operator) {
        T rsl = value.get(0);
        for (T el : value) {
            rsl = operator.apply(el, rsl);
        }
        return rsl;
    }
}