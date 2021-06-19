package ru.job4j.ood.parking.car;

public class CargoCar extends AbstractCar {
    public CargoCar(int id, int size) {
        super(id, size);
    }

    @Override
    public String toString() {
        return "CargoCar " + super.getId();
    }
}
