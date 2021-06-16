package ru.job4j.ood.menu;

import org.junit.Test;
import ru.job4j.ood.menu.builder.FlatMenuBuilder;
import ru.job4j.ood.menu.builder.TreeMenuBuilder;
import ru.job4j.ood.menu.util.ItemUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MenuTest {
    private static final String ln = System.lineSeparator();

    @Test
    public void whenBuildTreeMenu() {
        String menu = new TreeMenuBuilder(new ItemUtils().demo()).build();
        String expected = "0 Меню магазина (для выхода введите 0)" + ln +
                "--1 Купить" + ln +
                "----11 Яблоки" + ln +
                "------111 Симиренко" + ln +
                "------113 Гренни смит" + ln +
                "------114 Голден" + ln +
                "--2 Продать" + ln +
                "----21 Груши" + ln +
                "----22 Огурцы" + ln;
        assertThat(menu, is(expected));
    }

    @Test
    public void whenBuildFlatMenu() {
        String menu = new FlatMenuBuilder(new ItemUtils().demo()).build();
        String expected = "0 Меню магазина (для выхода введите 0)" + ln +
                "1 Купить" + ln +
                "11 Яблоки" + ln +
                "111 Симиренко" + ln +
                "113 Гренни смит" + ln +
                "114 Голден" + ln +
                "2 Продать" + ln +
                "21 Груши" + ln +
                "22 Огурцы" + ln;
        assertThat(menu, is(expected));
    }
}