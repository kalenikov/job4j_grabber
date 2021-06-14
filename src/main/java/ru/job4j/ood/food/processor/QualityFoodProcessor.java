package ru.job4j.ood.food.processor;

import ru.job4j.ood.food.DateUtils;
import ru.job4j.ood.food.model.Food;
import ru.job4j.ood.food.rule.DiscountRule;
import ru.job4j.ood.food.rule.StorageRule;

import java.time.LocalDate;
import java.util.List;

/**
 * класс, обрабатывающий правила перемещения в хранилища и правила установки скидок
 */

public class QualityFoodProcessor implements FoodProcessor {
    private List<StorageRule> storageRules;
    private List<DiscountRule> discountRules;
    private LocalDate now;

    public QualityFoodProcessor(List<StorageRule> storageRules, List<DiscountRule> discountRules, LocalDate now) {
        this.storageRules = storageRules;
        this.discountRules = discountRules;
        this.now = now;
    }

    public QualityFoodProcessor(List<StorageRule> storageRules, List<DiscountRule> discountRules) {
        this(storageRules, discountRules, LocalDate.now());
    }

    public void setNow(LocalDate now) {
        this.now = now;
    }

    @Override
    public void process(List<Food> foods) {
        foods.forEach(this::processFood);
    }

    private void processFood(Food food) {
        processStorageRules(food, DateUtils.getPercentRemainToExpiration(food, now));
        processFoodRules(food, DateUtils.getPercentRemainToExpiration(food, now));
    }

    private void processFoodRules(Food food, int percentRemainToExpiration) {
        discountRules.forEach(rule -> {
            if (rule.test(percentRemainToExpiration)) {
                food.setDiscount(rule.get());
            }
        });
    }

    private void processStorageRules(Food food, int percentRemainToExpiration) {
        storageRules.forEach(rule -> {
            if (rule.test(percentRemainToExpiration)) {
                rule.get().add(food);
            }
        });
    }
}
