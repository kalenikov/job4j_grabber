package ru.job4j.ood.parking.car;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCar that = (AbstractCar) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
