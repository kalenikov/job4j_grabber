package ru.job4j.ood.parking;

import ru.job4j.ood.parking.car.Car;

import java.util.Collection;

public class Parking implements ParkingService {
    private final StorageService storage;

    public Parking(StorageService storage) {
        this.storage = storage;
    }

    @Override
    public Collection<Car> getAllCars() {
        return null;
    }

    @Override
    public Car info(int id) {
        return null;
    }

    @Override
    public void park(Car car) {

    }

    @Override
    public void unpark(Car car) {

    }

    @Override
    public int getAvailableSpace() {
        return 0;
    }

}
