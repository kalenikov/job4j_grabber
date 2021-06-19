package ru.job4j.ood.parking;

import org.junit.jupiter.api.*;
import ru.job4j.ood.parking.car.Car;
import ru.job4j.ood.parking.car.CargoCar;
import ru.job4j.ood.parking.car.PassengerCar;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ParkingTest {
    public static ParkingService parking;
    public static List<Car> cargoCars = new ArrayList<>();
    public static List<Car> pasCars = new ArrayList<>();
    public static final int SIZE = 30;
    public static final int CARGO_SIZE = 10;

    @BeforeAll
    static void setUp() {
        List.of(1, 2, 3).forEach(id -> cargoCars.add(new CargoCar(id, CARGO_SIZE)));
        List.of(4, 5, 6).forEach(id -> pasCars.add(new PassengerCar(id)));
    }

    @BeforeEach
    void init() {
        parking = new Parking(new CarStorage(SIZE));
    }

    @Nested
    class ValidCases {
        @Test
        void park() throws ParkingException {
            parking.park(cargoCars.get(0));
            parking.park(pasCars.get(0));
            assertEquals(parking.getAllCars(), List.of(cargoCars.get(0), pasCars.get(0)));
            assertEquals(parking.getAvailableSpace(), SIZE - CARGO_SIZE - 1);
        }

        @Test
        @DisplayName("checking cars information")
        void info() throws ParkingException {
            parking.park(cargoCars.get(2));
            assertEquals(parking.info(3), cargoCars.get(2));
            assertNull(parking.info(100));
        }

        @Test
        @DisplayName("parking and un-parking flow")
        void parkingFlow() throws ParkingException {
            parking.park(cargoCars.get(0));
            parking.park(pasCars.get(0));
            parking.park(pasCars.get(1));
            assertEquals(parking.getAllCars(), List.of(cargoCars.get(0), pasCars.get(0), pasCars.get(1)));
            assertEquals(parking.getAvailableSpace(), SIZE - CARGO_SIZE - 1 - 1);
            parking.unpark(cargoCars.get(0));
            parking.unpark(pasCars.get(0));
            assertEquals(parking.getAvailableSpace(), SIZE - 1);
            parking.unpark(pasCars.get(1));
            assertTrue(parking.getAllCars().isEmpty());
            assertEquals(parking.getAvailableSpace(), SIZE);
        }
    }

    @Nested
    class InvalidCases {
        @Test
        @DisplayName("attempt to re-park the same car")
        void repeatedPark() throws ParkingException {
            parking.park(cargoCars.get(0));
            ParkingException ex = assertThrows(ParkingException.class, () -> {
                parking.park(cargoCars.get(0));
            });
            assertEquals(ex.getMessage(), "attempt to re-park a car with id 1");
        }

        @Test
        @DisplayName("attempt to overload the parking size")
        void overloadPark() throws ParkingException {
            parking.park(cargoCars.get(0));
            parking.park(cargoCars.get(1));
            parking.park(cargoCars.get(2));
            ParkingException ex = assertThrows(ParkingException.class, () -> {
                parking.park(pasCars.get(0));
            });
            assertEquals(ex.getMessage(), "not enough space for car 4");
        }

        @Test
        @DisplayName("attempt to unpark a car that is not in the parking")
        void invalidUnpark() throws ParkingException {
            ParkingException ex = assertThrows(ParkingException.class, () -> {
                parking.unpark(cargoCars.get(0));
            });
            assertEquals(ex.getMessage(), "car 1 is not in the parking storage");
        }

        @Test
        @DisplayName("attempt to repeat parking cargo in previously free slot")
        void invalidParkingFlow() throws ParkingException {
            parking.park(cargoCars.get(0));
            parking.park(pasCars.get(0));
            parking.park(cargoCars.get(1));
            parking.park(pasCars.get(1));
            assertEquals(parking.getAvailableSpace(), SIZE - CARGO_SIZE - 1 - CARGO_SIZE - 1);
            parking.unpark(cargoCars.get(1));
            assertEquals(parking.getAvailableSpace(), SIZE - CARGO_SIZE - 1 - 1);
            // на месте cargoCar2 образовалась свободное место
            // ставим на свободное место pasCar
            parking.park(pasCars.get(2));
            assertEquals(parking.getAvailableSpace(), SIZE - CARGO_SIZE - 1 - 1 - 1);
            // cargoCar2 назад не влазит, несмотря на то, что общее свободное место = 17,
            // но оно поделено на две локации - 9 и 8
            ParkingException ex = assertThrows(ParkingException.class, () -> {
                parking.park(cargoCars.get(1));
            });
            assertEquals(ex.getMessage(), "not enough space for car 2");
        }
    }
}