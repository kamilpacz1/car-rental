package car;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Car {
    private String brand;
    private String model;
    private int mileage;
    private LocalDate yearOfProduction;
    private double pricePerDay;
    private BigDecimal pricePerDayBD;

    public Car(String brand, String model, int mileage, LocalDate yearOfProduction, double pricePerDay, BigDecimal pricePerDayBD) {
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.yearOfProduction = yearOfProduction;
        this.pricePerDay = pricePerDay;
        this.pricePerDayBD = pricePerDayBD;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getCarDisplayInformation() {
        String format = "%s - %s. Mileage: %d, year of production: %s, price per day: %.2f";
        String yearOfProductionFormatted = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(this.yearOfProduction);
        return String.format(format, brand, model, mileage, yearOfProductionFormatted, pricePerDay);
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;

    }

    public BigDecimal getPricePerDayBD() {
        return pricePerDayBD;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }
}
