package ru.job4j.ood.parking.car;

public class PassengerCar extends AbstractCar {
    public PassengerCar(int id) {
        super(id, 1);
    }

      @Override
    public String toString() {
        return "PassengerCar " + super.getId();
    }
}
