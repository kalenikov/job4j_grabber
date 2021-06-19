package ru.job4j.ood.food;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.ood.food.model.BalancedFoodStorage;
import ru.job4j.ood.food.model.Food;
import ru.job4j.ood.food.processor.QualityFoodProcessor;
import ru.job4j.ood.food.rule.DiscountRule;
import ru.job4j.ood.food.rule.StorageRule;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class QualityFoodProcessorTest {
    private static QualityFoodProcessor qfc;
    private static BalancedFoodStorage shop;
    private static BalancedFoodStorage warehouse;
    private static BalancedFoodStorage trash;
    private static Food food1;
    private static Food food2;

    @BeforeClass
    public static void setUp() {
        shop = new BalancedFoodStorage();
        warehouse = new BalancedFoodStorage();
        trash = new BalancedFoodStorage();

        List<StorageRule> storageRules = List.of(
                new StorageRule(p -> p <= 0, trash),
                new StorageRule(p -> p > 0 & p < 25, shop),
                new StorageRule(p -> p >= 25 & p < 75, warehouse),
                new StorageRule(p -> p > 75, shop)
        );
        List<DiscountRule> discountRules = List.of(
                new DiscountRule(p -> p > 75, 10)
        );

        qfc = new QualityFoodProcessor(
                storageRules,
                discountRules);

        LocalDate date = LocalDate.of(2020, 1, 1);
        food1 = new Food("food1", date, date.plusDays(100), 100);
        food2 = new Food("food2", date, date.plusDays(100), 200);
    }

    @Before
    public void clear() {
        shop.clear();
        warehouse.clear();
        trash.clear();
        food1.setDiscount(0);
        food2.setDiscount(0);
    }

    @Test
    public void moveWhenPassed365PercentExpDate() {
        qfc.setNow(LocalDate.of(2021, 1, 1));
        qfc.process(List.of(food1, food2));
        assertThat(trash.getAll(), is(List.of(food1, food2)));
        assertThat(shop.getAll().size(), is(0));
        assertThat(warehouse.getAll().size(), is(0));
    }

    @Test
    public void moveWhenPassed20PercentExpDate() {
        qfc.setNow(LocalDate.of(2020, 1, 20));
        qfc.process(List.of(food1, food2));
        assertThat(trash.getAll().size(), is(0));
        assertThat(shop.getAll(), is(List.of(food1, food2)));
        assertThat(warehouse.getAll().size(), is(0));
    }

    @Test
    public void moveWhenPassed60PercentExpDate() {
        qfc.setNow(LocalDate.of(2020, 3, 1));
        qfc.process(List.of(food1, food2));
        assertThat(trash.getAll().size(), is(0));
        assertThat(shop.getAll().size(), is(0));
        assertThat(warehouse.getAll(), is(List.of(food1, food2)));
    }

    @Test
    public void setDiscountWhenPassed80PercentExpDate() {
        qfc.setNow(LocalDate.of(2020, 3, 25));
        qfc.process(List.of(food1));
        assertThat(food1.getDiscount(), is(10.0));
    }

    @Test
    public void checkDiscountWhenPassed21PercentExpDate() {
        qfc.setNow(LocalDate.of(2020, 1, 10));
        qfc.process(List.of(food1));
        assertThat(food1.getDiscount(), is(0.0));
    }

    @Test
    public void whenResort() {
        // pass 20% expiration date: expected location is shop
        qfc.setNow(LocalDate.of(2020, 1, 20));
        qfc.resort(List.of(food1));
        assertThat(trash.getAll(), is(List.of()));
        assertThat(shop.getAll(), is(List.of(food1)));
        assertThat(warehouse.getAll(), is(List.of()));

        // pass 60% expiration date: expected location is warehouse
        qfc.setNow(LocalDate.of(2020, 3, 1));
        qfc.resort(List.of(food1));
        assertThat(trash.getAll(), is(List.of()));
        assertThat(shop.getAll(), is(List.of()));
        assertThat(warehouse.getAll(), is(List.of(food1)));

        // pass 100% expiration date: expected location is trash
        qfc.setNow(LocalDate.of(2021, 1, 20));
        qfc.resort(List.of(food1));
        assertThat(trash.getAll(), is(List.of(food1)));
        assertThat(shop.getAll(), is(List.of()));
        assertThat(warehouse.getAll(), is(List.of()));

    }
}