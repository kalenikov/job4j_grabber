package ru.job4j.ood.parking;

import ru.job4j.ood.parking.car.Car;

import java.util.Collection;

public class CarStorage implements StorageService {
    private final int size;

    public CarStorage(int size) {
        this.size = size;
    }

    @Override
    public Collection<Car> getAll() {
        return null;
    }

    @Override
    public Car get(int id) {
        return null;
    }

    @Override
    public void add(Car car) {

    }

    @Override
    public void remove(Car car) {

    }
}
