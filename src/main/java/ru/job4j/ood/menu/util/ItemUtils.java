package ru.job4j.ood.menu.util;

import ru.job4j.ood.menu.model.Item;
import ru.job4j.ood.menu.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {
    public List<Item> demo() {
        Item root = new MenuItem(0, null, "Меню магазина (для выхода введите 0)", () -> false);
        Item buy = new MenuItem(1, root, "Купить");
        Item sell = new MenuItem(2, root, "Продать");
        Item apple = new MenuItem(11, buy, "Яблоки");
        List<Item> items = new ArrayList<>();
        items.add(root);
        items.add(buy);
        items.add(sell);
        items.add(apple);
        items.add(new MenuItem(111, apple, "Симиренко"));
        items.add(new MenuItem(22, sell, "Огурцы", () -> {
            System.out.println("вы купили огурцы");
            return true;
        }));
        items.add(new MenuItem(21, buy, "Груши"));
        items.add(new MenuItem(113, apple, "Гренни смит"));
        items.add(new MenuItem(114, apple, "Голден",() -> {
            System.out.println("вы продали яблоки голден");
            return true;
        }));
        return items;
    }
}
