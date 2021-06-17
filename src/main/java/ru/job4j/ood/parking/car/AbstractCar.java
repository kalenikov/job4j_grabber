package ru.job4j.ood.parking.car;

public abstract class AbstractCar implements Car {
    private final int id;
    private final int size;

    public AbstractCar(int id, int size) {
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
