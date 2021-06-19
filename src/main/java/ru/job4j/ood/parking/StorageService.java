package ru.job4j.ood.parking;

import ru.job4j.ood.parking.car.Car;

import java.util.List;

/**
 * сервис контролирует размещение машин в хранилище машин
 */

public interface StorageService {
    List<Car> getAll();

    Car get(int id);

    void add(Car car) throws ParkingException;

    void remove(Car car) throws ParkingException;

    int getAvailableSpace();
}
