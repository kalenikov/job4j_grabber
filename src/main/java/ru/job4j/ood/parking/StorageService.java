package ru.job4j.ood.parking;

import ru.job4j.ood.parking.car.Car;

import java.util.Collection;

/**
 * сервис контролирует размещение машин в хранилище машин
 */

public interface StorageService {
    Collection<Car> getAll();

    Car get(int id);

    void add(Car car);

    void remove(Car car);
}
