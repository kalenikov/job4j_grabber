package ru.job4j.ood.parking;

import ru.job4j.ood.parking.car.Car;

import java.util.*;
import java.util.stream.Collectors;

public class CarStorage implements StorageService {
    private final Car[] data;
    // mapping for car id --> cell index
    private final Map<Integer, Integer> index = new HashMap<>();

    public CarStorage(int size) {
        data = new Car[size];
    }

    @Override
    public List<Car> getAll() {
        return index.values().stream().map(i -> data[i]).collect(Collectors.toList());
    }

    @Override
    public Car get(int id) {
        if (index.get(id) != null) {
            return data[index.get(id)];
        }
        return null;
    }

    @Override
    public void add(Car car) throws ParkingException {
        if (index.containsKey(car.getId())) {
            throw new ParkingException("attempt to re-park a car with id " + car.getId());
        }
        int freeCell = lookupFreeSpace(car.getSize());
        if (freeCell == -1 || car.getSize() > getAvailableSpace()) {
            throw new ParkingException("not enough space for car " + car.getId());
        }
        data[freeCell] = car;
        index.put(car.getId(), freeCell);
    }

    @Override
    public void remove(Car car) throws ParkingException {
        if (!index.containsKey(car.getId())) {
            throw new ParkingException("car " + car.getId() + " is not in the parking storage");
        }
        data[index.get(car.getId())] = null;
        index.remove(car.getId());
    }

    public int getAvailableSpace() {
        return data.length - index.values().stream()
                .mapToInt(i -> data[i].getSize())
                .sum();
    }

    private int lookupFreeSpace(int size) {
        // looking for free space by direct search
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                // looking for all size from found cell
                for (int j = i; j < i + size; j++) {
                    if (data[j] != null) {
                        return -1;
                    }
                }
                return i;
            }
            // skip all occupied cells
            i += data[i].getSize() - 1;
        }
        return -1;
    }
}
