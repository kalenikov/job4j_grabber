package ru.job4j.ood.menu.io;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public int nextInt() {
        return scanner.nextInt();
    }
}
