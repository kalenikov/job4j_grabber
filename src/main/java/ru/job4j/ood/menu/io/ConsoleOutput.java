package ru.job4j.ood.menu.io;

public class ConsoleOutput implements Output {
    @Override
    public void print(String string) {
        System.out.println(string);
    }
}
