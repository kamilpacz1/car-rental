package rental;

import array.ArrayCompactor;
import car.Car;
import car.CarStorage;
import user.User;
import user.UserStorage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Scanner;

import static org.apache.commons.math3.util.Precision.round;

public class Rental {
    private RentalEntry[] rentals = new RentalEntry[100];
    private int rentalCount;
    private RentalEntry[] rentalArchive = new RentalEntry[100];
    private int rentalCountArchive;
    private CarStorage carStorage;
    private UserStorage userStorage;
    private Math Precision;

    public Rental(CarStorage carStorage, UserStorage userStorage) {
        this.carStorage = carStorage;
        this.userStorage = userStorage;
    }

    public void rentACar() {
        User user = userStorage.selectuser();
        if (user == null) {
            System.out.println("Invaild user selected");
            return;
        }
        Car carToRent = carStorage.selectCar();
        if (carToRent == null) {
            System.out.println("Invaild car selected");
            return;
        }
        rentActualUserCar(user, carToRent);

    }

    public void listAllActiveRentedCars() {
        System.out.println("Active rentals: ");
        for (int i = 0; i < rentals.length && rentals[i] != null; i++) {
            System.out.println((i + 1) + ": " + rentals[i].getDisplayInfo());
        }
    }


    public void returnACar() {
        listAllActiveRentedCars();
        Scanner scanner = new Scanner(System.in);
        int rentalToFinish = scanner.nextInt() - 1;
        if (rentalToFinish < 0 || rentalToFinish > rentalCount) {
            System.out.println("Invaild rental id to return");
            return;
        }
        RentalEntry finishedRental = rentals[rentalToFinish];
        rentalArchive[rentalCountArchive++] = finishedRental;
        --rentalCount;
        ArrayCompactor.compactRentalEntryArray(rentals);
        carStorage.returnACar(finishedRental.getCar());
    }

    public RentalEntry[] getRentalArchive() {
        return rentalArchive;
    }


    private void rentActualUserCar(User user, Car carToRent) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("For how many days? ");
        int numberOfDays = Integer.parseInt(scanner.nextLine());
        double price = calculatePrice(user, carToRent, numberOfDays);
        BigDecimal priceBD = calculatePriceBD(user, carToRent, numberOfDays);
        System.out.printf("Rent will cost %.2f. Are you sure? [yes/no] ", price);
        String rentAnswer = scanner.nextLine();
        if ("yes".equalsIgnoreCase(rentAnswer)) {
            RentalEntry entry = new RentalEntry(carToRent, user, LocalDateTime.now(), numberOfDays, price, priceBD);
            rentals[rentalCount] = entry;
            ++rentalCount;
            user.incrementCarsRenter();
            carStorage.addRentalCar(carToRent);
            System.out.println("Car rented");
        } else {
            System.out.println("Aborted");
        }
    }

    private double calculatePrice(User user, Car car, int numberOfDays) {
        double basePrice = numberOfDays * car.getPricePerDay();
        double discount = 0;
        if (user.getCarsRentedSoFar() > 100) {
            discount += 0.1;
        }
        if (user.isAgeOver(40)) {
            discount += 0.1;
        }
        return round(basePrice * (1 - discount), 2, BigDecimal.ROUND_UP);
    }

    private BigDecimal calculatePriceBD(User user, Car car, int numberOfDays) {
        BigDecimal basePrice = car.getPricePerDayBD().multiply(BigDecimal.valueOf(numberOfDays));
        BigDecimal discount = BigDecimal.ONE;
        if (user.getCarsRentedSoFar() > 100) {
            discount = discount.add(new BigDecimal("0.1"));
        }
        if (user.isAgeOver(40)) {
            discount = discount.add(new BigDecimal("0.1"));
        }
        return basePrice.multiply(BigDecimal.ONE.subtract(discount)).setScale(2, RoundingMode.UP);
    }
}
