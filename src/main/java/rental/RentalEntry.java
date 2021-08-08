package rental;

import car.Car;
import user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RentalEntry {
    private Car car;
    private User user;
    private LocalDateTime rentalStartTime;
    private int rentalDurationDays;
    private double cost;
    private BigDecimal costBD;

    public RentalEntry(Car car, User user, LocalDateTime rentalStartTime, int rentalDurationDays, double cost, BigDecimal costBD) {
        this.car = car;
        this.user = user;
        this.rentalStartTime = rentalStartTime;
        this.rentalDurationDays = rentalDurationDays;
        this.cost = cost;
        this.costBD = costBD;
    }

    public Car getCar() {
        return car;
    }

    public LocalDateTime getRentalStartTime() {
        return rentalStartTime;
    }

    public double getCost() {
        return cost;
    }

    public BigDecimal getCostBD() {
        return costBD;
    }

    public String getDisplayInfo() {
        return String.format("%s %s rents car %s %s for %d days for a total cost of %.2f",
                user.getName(), user.getLastName(), car.getBrand(), car.getModel(), rentalDurationDays, cost);
    }
}
