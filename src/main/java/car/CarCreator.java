package car;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CarCreator {
    private CarStorage carStorage;

    public CarCreator(CarStorage carStorage) {
        this.carStorage = carStorage;
    }

    public void createNewCar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Specify data for new car:");
        System.out.println("Specify brand: ");
        String brand = scanner.nextLine();
        if(brand.isEmpty()) {
            System.out.println("Invaild brand - exiting . . .");
            return;
        }
        System.out.println("Specify model");
        String model = scanner.nextLine();
        if(model.isEmpty()) {
            System.out.println("Invaild model - exiting . . . ");
            return;
        }
        System.out.println("Specify mileage");
        String rawMileage = scanner.nextLine();
        if(!Pattern.compile("\\d+").matcher(rawMileage).matches()) {
            System.out.println("Invaild milage - exiting . . . ");
            return;
        }
        int mileage = Integer.parseInt(rawMileage);

        System.out.println("Specify year of production(in formt yyyy-mm-dd): ");
        String rawYearOfProduction = scanner.nextLine();
        if(!Pattern.compile("\\d{4}-\\d{2}-\\d{2}").matcher(rawYearOfProduction).matches()) {
            System.out.println("Invaild year of production - exiting . . .");
            return;
        }
        LocalDate yearOfproduction = LocalDate.parse(rawYearOfProduction, DateTimeFormatter.ISO_DATE_TIME);

        System.out.println("Specify price per day: ");
        String rawPricePerDay = scanner.nextLine();
        if(!Pattern.compile("\\d+\\.?\\d+").matcher(rawPricePerDay).matches()) {
            System.out.println("Invaild price per day - exiting . . .");
            return;
        }
        double pricePerDay = Double.parseDouble(rawPricePerDay);
        if(pricePerDay <= 0) {
            System.out.println("Daily rent must be greater than zero - exiting . . .");
            return;
        }

        Car newCar = new Car(brand, model, mileage, yearOfproduction, pricePerDay, new BigDecimal(rawPricePerDay));
        carStorage.addCar(newCar);
    }
}
