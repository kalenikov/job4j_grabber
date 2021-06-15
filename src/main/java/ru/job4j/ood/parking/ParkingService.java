package ru.job4j.ood.parking;

import ru.job4j.ood.parking.car.Car;

import java.util.Collection;

/**
 * сервис предоставляет доступ к хранилищу машин
 * и не знает подробностей его реализации
 */
public interface ParkingService {
    Collection<Car> getAllCars();

    Car info(int id);

    void park(Car car);

    void unpark(Car car);
}
