package ru.job4j.ood.menu;

import ru.job4j.ood.menu.builder.*;
import ru.job4j.ood.menu.io.ConsoleInput;
import ru.job4j.ood.menu.io.ConsoleOutput;
import ru.job4j.ood.menu.io.Input;
import ru.job4j.ood.menu.io.Output;
import ru.job4j.ood.menu.model.Item;
import ru.job4j.ood.menu.repository.InMemoryItemRepository;
import ru.job4j.ood.menu.repository.ItemRepository;

import java.util.List;

public class App {
    private final Output out;
    private final Input input;
    private final ItemRepository repo;

    public App(Input input, Output out, ItemRepository repo) {
        this.out = out;
        this.repo = repo;
        this.input = input;
    }

    public static void main(String[] args) {
        new App(new ConsoleInput(),
                new ConsoleOutput(),
                new InMemoryItemRepository())
                .run();
    }

    private void run() {
        List<Item> menu = new InMemoryItemRepository().getAll();
        out.print(new TreeMenuBuilder(menu).build());
        boolean run = true;
        while (run) {
            int in = input.nextInt();
            Item item = repo.get(in);
            if (item == null) {
                out.print("Выбран несуществующий пункт меню, попробуйте снова");
                continue;
            }
            out.print("Вы выбрали: " + item.getLabel());
            run = item.execute();
        }
    }
}
