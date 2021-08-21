import car.Car;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public class Application {

    public static void main(String[] args) {
        Car[] initialCars = buildInitialCars();
        ConsoleApplication consoleApplication = new ConsoleApplication();
        consoleApplication.run(initialCars);
    }

    private static Car[] buildInitialCars() {
        Car opelCorsa = new Car("Opel", "Corsa", 120000, LocalDate.of(2010, Month.APRIL, 13), 80, new BigDecimal("80"));
        return new Car[]{
                opelCorsa
        };
    }
}
