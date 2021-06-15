package ru.job4j.ood.parking.car;

public abstract class AbstractCar implements Car {
    private int size;
    private int id;

    public AbstractCar(int size, int id) {
        this.size = size;
        this.id = id;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getId() {
        return id;
    }
}
