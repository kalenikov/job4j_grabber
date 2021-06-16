package ru.job4j.ood.menu.builder;

import ru.job4j.ood.menu.model.Item;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMenuBuilder implements MenuBuilder {
    private final List<Item> items;

    public FlatMenuBuilder(List<Item> items) {
        this.items = items;
    }

    @Override
    public String build() {
        StringBuilder sb = new StringBuilder();
        List<Item> sorted = items.stream()
                .sorted(Comparator.comparing(String::valueOf))
                .collect(Collectors.toList());
        for (Item i : sorted) {
            sb.append(i.getId())
                    .append(" ")
                    .append(i.getLabel())
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}
