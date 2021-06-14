package ru.job4j.ood.food;

import ru.job4j.ood.food.model.Food;
import ru.job4j.ood.food.model.FoodStorage;
import ru.job4j.ood.food.model.Storage;
import ru.job4j.ood.food.processor.QualityFoodProcessor;
import ru.job4j.ood.food.rule.DiscountRule;
import ru.job4j.ood.food.rule.StorageRule;

import java.time.LocalDate;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Storage<Food> shop = new FoodStorage();
        Storage<Food> warehouse = new FoodStorage();
        Storage<Food> trash = new FoodStorage();
        // p is percentRemainToExpiration
        List<StorageRule> storageRules = List.of(
                new StorageRule(p -> p <= 0, trash),
                new StorageRule(p -> p > 0 & p < 25, shop),
                new StorageRule(p -> p >= 25 & p < 75, warehouse),
                new StorageRule(p -> p > 75, shop)
        );
        List<DiscountRule> discountRules = List.of(
                new DiscountRule(p -> p > 75, 10)
        );
        QualityFoodProcessor qfc = new QualityFoodProcessor(
                storageRules,
                discountRules);
        List<Food> foods = List.of(
                new Food("food1", LocalDate.now(), LocalDate.now().plusDays(100), 100),
                new Food("food2", LocalDate.now(), LocalDate.now().plusDays(100), 200)
        );
        qfc.process(foods);
    }
}
