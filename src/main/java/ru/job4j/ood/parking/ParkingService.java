package ru.job4j.ood.parking;

import ru.job4j.ood.parking.car.Car;

import java.util.List;

/**
 * сервис предоставляет доступ к хранилищу машин
 * и не знает подробностей его реализации
 */
public interface ParkingService {
    List<Car> getAllCars();

    Car info(int id);

    void park(Car car) throws ParkingException;

    void unpark(Car car) throws ParkingException;

    int getAvailableSpace();
}
