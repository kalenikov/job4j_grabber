package ru.job4j.ood.food.rule;

import java.util.function.Predicate;

public abstract class AbstractRule<T, R> {
    private final Predicate<T> predicate;
    private final R result;

    public AbstractRule(Predicate<T> predicate, R result) {
        this.predicate = predicate;
        this.result = result;
    }

    public boolean test(T t) {
        return predicate.test(t);
    }

    public R get() {
        return result;
    }
}
