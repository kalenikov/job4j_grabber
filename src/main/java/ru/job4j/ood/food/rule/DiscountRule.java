package ru.job4j.ood.food.rule;

import java.util.function.Predicate;

/**
 * класс, описывающий условия применение скидок
 */

public class DiscountRule extends AbstractRule<Integer, Integer> {
    public DiscountRule(Predicate<Integer> predicate, Integer result) {
        super(predicate, result);
    }
}
