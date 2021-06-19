package ru.job4j.ood.parking;

import ru.job4j.ood.parking.car.Car;

import java.util.List;

public class Parking implements ParkingService {
    private final StorageService storage;

    public Parking(StorageService storage) {
        this.storage = storage;
    }

    @Override
    public List<Car> getAllCars() {
        return storage.getAll();
    }

    @Override
    public Car info(int id) {
        return storage.get(id);
    }

    @Override
    public void park(Car car) throws ParkingException {
        storage.add(car);
    }

    @Override
    public void unpark(Car car) throws ParkingException {
        storage.remove(car);
    }

    @Override
    public int getAvailableSpace() {
        return storage.getAvailableSpace();
    }

}
